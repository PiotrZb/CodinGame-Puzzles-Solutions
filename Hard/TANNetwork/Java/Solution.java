import java.util.ArrayList;
import java.util.Scanner;

class Solution {

    // reading data from standard input
    private static ArrayList<Object> readData() {
        Scanner scanner = new Scanner(System.in);
        String startPoint = scanner.next();
        String endPoint = scanner.next();
        Network TANNetwork = new Network();
        int numberOfStops = scanner.nextInt();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        for (int i = 0; i < numberOfStops; i++) {
            String[] stopData = scanner.nextLine().split(",");
            TANNetwork.addStop(new TANStop(stopData[0], stopData[1].replaceAll("\"", ""),
                    Math.toRadians(Double.parseDouble(stopData[3])), Math.toRadians(Double.parseDouble(stopData[4]))));
        }

        int numberOfRoutes = scanner.nextInt();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        for (int i = 0; i < numberOfRoutes; i++) {
            String[] route = scanner.nextLine().split(" ");
            TANNetwork.addRoute(route[0], route[1]);
        }

        ArrayList<Object> data = new ArrayList<>();
        data.add(startPoint);
        data.add(endPoint);
        data.add(TANNetwork);
        return data;
    }

    public static void main(String[] args) {
        ArrayList<Object> data = readData();
        String startPoint = (String) data.get(0);
        String endPoint = (String) data.get(1);
        Network TANNetwork = (Network) data.get(2);
        ArrayList<String> route = TANNetwork.findShortestRoute(startPoint, endPoint);
        // printing answer on standard output
        for (String stop : route) {
            System.out.println(stop);
        }
    }
}