import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    // Private attributes
    private T root;
    private List<Tree<T>> subtrees;

    // Constructor
    public Tree(T root, List<Tree<T>> subtrees) {
        this.root = root;
        if (subtrees == null) {
            this.subtrees = new ArrayList<>();
        } else {
            this.subtrees = new ArrayList<>(subtrees);
        }
    }

    // Method to check if the tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Method to return the number of items in the tree
    public int size() {
        if (isEmpty()) {
            return 0;
        } else {
            int size = 1;  // count the root
            for (Tree<T> subtree : subtrees) {
                size += subtree.size();
            }
            return size;
        }
    }

    // Method to count the occurrences of an item in the tree
    public int count(T item) {
        if (isEmpty()) {
            return 0;
        } else {
            int num = 0;
            if (root.equals(item)) {
                num += 1;
            }
            for (Tree<T> subtree : subtrees) {
                num += subtree.count(item);
            }
            return num;
        }
    }

    // Method to return a string representation of the tree
    @Override
    public String toString() {
        return toStringIndented(0);
    }

    // Helper method for toString to handle indentation
    private String toStringIndented(int depth) {
        if (isEmpty()) {
            return "";
        } else {
            StringBuilder s = new StringBuilder("  ".repeat(depth) + root + "\n");
            for (Tree<T> subtree : subtrees) {
                s.append(subtree.toStringIndented(depth + 1));
            }
            return s.toString();
        }
    }

    // Method to calculate the average of all values in the tree
    public double average() {
        if (isEmpty()) {
            return 0.0;
        } else {
            Pair<Integer, Integer> result = averageHelper();
            return (double) result.getFirst() / result.getSecond();
        }
    }

    // Helper method for average to calculate total and size
    private Pair<Integer, Integer> averageHelper() {
        if (isEmpty()) {
            return new Pair<>(0, 0);
        } else {
            int total = (Integer) root;
            int size = 1;
            for (Tree<T> subtree : subtrees) {
                Pair<Integer, Integer> subtreeResult = subtree.averageHelper();
                total += subtreeResult.getFirst();
                size += subtreeResult.getSecond();
            }
            return new Pair<>(total, size);
        }
    }

    // Pair class for handling two return values in averageHelper
    private static class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }
}
