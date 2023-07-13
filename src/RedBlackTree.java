public class RedBlackTree {

    private Node root;

    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.black;
            return  result;
        } else {
            root = new Node();
            root.color = Color.black;
            root.value = value;
            return true;
        }
    }

    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.leftChild != null) {
                    boolean result = addNode(node.leftChild, value);
                    node.leftChild = rebalance(node.leftChild);
                    return result;
                } else {
                    node.leftChild = new Node();
                    node.leftChild.color = Color.red;
                    node.leftChild.value = value;
                    return true;
                }
            } else {
                if (node.rightChild != null) {
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.red;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.rightChild != null && result.rightChild.color == Color.red &&
                    (result.leftChild == null || result.leftChild.color == Color.black)) {
                    needRebalance = true;
                    result = rightSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.red &&
                    result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.red) {
                    needRebalance = true;
                    result = leftSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.red &&
                    result.rightChild != null && result.rightChild.color == Color.red) {
                    needRebalance = true;
                    colorSwap(result);
            }
        }
        while (needRebalance);
        return  result;
    }

    private Node rightSwap(Node node) {
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.red;
        return rightChild;
    }

    private Node leftSwap(Node node) {
        Node leftChild = node.leftChild;
        Node betweenchild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenchild;
        leftChild.color = node.color;
        node.color = Color.red;
        return leftChild;
    }

    private void colorSwap(Node node) {
        node.rightChild.color = Color.black;
        node.leftChild.color = Color.black;
        node.color = Color.red;
    }

    private void printTREE(Node root, String indent, boolean last) {
        if (root != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            Color nodeColor = root.color.equals(Color.red) ? Color.red : Color.black;
            System.out.println(root.value + "(" + nodeColor + ")");
            printTREE(root.leftChild, indent, false);
            printTREE(root.rightChild, indent, true);
        }
    }
    public void printTree(){
        printTREE(this.root, "", true);
    }

    private class Node {
        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color" + color +
                    "}";
        }
    }

    enum Color {red, black}
}
