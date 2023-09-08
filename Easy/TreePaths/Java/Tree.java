import java.util.ArrayList;
import java.util.Stack;

// class representing binary tree
class Tree{
    private ArrayList<Node> nodes;
    private Node root;

    public Tree(){
        this.nodes = new ArrayList<>();
        this.root = null;
    }

    public void addNode(int index, int leftIndex, int rightIndex){
        Node node = null;
        Node leftChild = null;
        Node rightChild = null;

        // checking if nodes already exist
        for (Node n : nodes){
            if (n.getIndex() == index){
                node = n;
            } else if (n.getIndex() == leftIndex) {
                leftChild = n;
            } else if (n.getIndex() == rightIndex) {
                rightChild = n;
            }
        }

        // creating nodes if not found
        if (node == null){
            node = new Node(index);
            this.nodes.add(node);
        }
        if (leftChild == null){
            leftChild = new Node(leftIndex);
            this.nodes.add(leftChild);
        }
        if (rightChild == null){
            rightChild = new Node(rightIndex);
            this.nodes.add(rightChild);
        }

        // creating connections
        node.setLeftChild(leftChild);
        node.setRightChild(rightChild);
        leftChild.setParent(node);
        rightChild.setParent(node);
    }

    // implementation of DFS algorithm
    public ArrayList<String> findPathToNode(int index){
        this.findRoot();

        ArrayList<String> answer = new ArrayList<>();

        if (this.root.getIndex() == index){
            answer.add("Root");
        }else{
            Node currentNode = this.root;
            Stack<Node> stack = new Stack<>();

            while (!(this.root.getLeftChild().isVisited() && this.root.getRightChild().isVisited() && currentNode == this.root)){
                currentNode.setVisited(true);

                // node found
                if (currentNode.getIndex() == index){
                    break;
                }

                // end of branch
                if (currentNode.getLeftChild() == null){
                    currentNode = stack.pop();
                    answer.remove(answer.size() - 1);
                }
                // choose left child
                else if (!currentNode.getLeftChild().isVisited()) {
                    stack.push(currentNode);
                    currentNode = currentNode.getLeftChild();
                    answer.add("Left");
                }
                // choose right child
                else if (!currentNode.getRightChild().isVisited()) {
                    stack.push(currentNode);
                    currentNode = currentNode.getRightChild();
                    answer.add("Right");
                }
                // both children visited
                else{
                    currentNode = stack.pop();
                    answer.remove(answer.size() - 1);
                }
            }
        }

        return answer;
    }

    private void findRoot(){
        for (Node node : this.nodes){
            if (node.getParent() == null){
                this.root = node;
                break;
            }
        }
    }
}