// class representing single node
class Node{

    private final int index;
    private Node parent, leftChild, rightChild;
    private boolean visited;
    
    public Node(int index){
        this.index = index;
        this.visited = false;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getIndex() {
        return this.index;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}