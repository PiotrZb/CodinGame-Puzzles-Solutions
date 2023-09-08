import java.util.Scanner;
import java.util.ArrayList;

class Solution {
    private static Tree tree;

    // reading data from standard input
    private static int readData(){
        Scanner scanner = new Scanner(System.in);
        int numberOfNodes = scanner.nextInt();
        int target = scanner.nextInt();
        int numberOfNodesWithChildren = scanner.nextInt();
        tree = new Tree();
        int node, leftChild, rightChild;
        for (int i = 0; i < numberOfNodesWithChildren; i++) {
            node = scanner.nextInt();
            leftChild = scanner.nextInt();
            rightChild = scanner.nextInt();
            tree.addNode(node, leftChild, rightChild);
        }

        return target;
    }

    public static void main(String[] args) {
        int targetIndex = readData();
        ArrayList<String> path = tree.findPathToNode(targetIndex);
        for (int i = 0; i < path.size(); i++){
            if (i < path.size() - 1){
                System.out.print(path.get(i) + " ");
            }else{
                System.out.print(path.get(i));
            }
        }
    }
}