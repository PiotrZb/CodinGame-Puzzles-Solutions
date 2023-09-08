import java.util.Scanner;

class Solution {

    // reading data from standard input
    private static GearBox readLayout(){
        GearBox gearBox = new GearBox();
        Scanner scanner = new Scanner(System.in);
        int numberOfGears = scanner.nextInt();

        for (int i = 0; i < numberOfGears; i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int r = scanner.nextInt();
            gearBox.addGear(x, y, r);
        }

        return gearBox;
    }

    public static void main(String[] args) {
        GearBox gearBox = readLayout();
        String answer = gearBox.solve();
        System.out.println(answer);
    }
}