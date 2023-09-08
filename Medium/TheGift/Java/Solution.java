import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Solution {

    private static int totalCost, numberOfParticipants;
    private static ArrayList<Integer> listOfBudgets;

    // reading data from standard input
    private static void readData(){
        Scanner scanner = new Scanner(System.in);
        numberOfParticipants = scanner.nextInt();
        totalCost = scanner.nextInt();
        listOfBudgets = new ArrayList<>();
        for (int i = 0; i < numberOfParticipants; i++) {
            listOfBudgets.add(scanner.nextInt());
        }
    }

    // greedy algorithm
    private static void generateAnswer(){
        if (calculateSumOfBudgets() < totalCost){
            System.out.println("IMPOSSIBLE");
        }else{
            int average, minBudget = Collections.min(listOfBudgets);
            while(totalCost > 0){
                average = totalCost/numberOfParticipants;
                if (minBudget < average){
                    System.out.println(minBudget);
                    totalCost -= minBudget;
                }else{
                    System.out.println(average);
                    totalCost -= average;
                }
                listOfBudgets.remove((Integer) minBudget);
                if (!listOfBudgets.isEmpty()){
                    minBudget = Collections.min(listOfBudgets);
                }
                numberOfParticipants--;
            }
        }
    }

    private static int calculateSumOfBudgets(){
        int sum = 0;
        for (int budget : listOfBudgets){
            sum += budget;
        }
        return sum;
    }

    public static void main(String[] args) {
        readData();
        generateAnswer();
    }
}