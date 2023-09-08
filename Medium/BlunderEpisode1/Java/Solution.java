import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    private static boolean looped, mapModified, suicideBoothReached, breakerMode, moveAllowed, directionChanged, loopIsChecked;
    private static int height, width, currentX, currentY;
    private static String currentDirection, nextDirection;
    private static char[][] map;
    private static int[] teleport1, teleport2;
    private static ArrayList<String> priorities;
    private static ArrayList<Position> positionsHistory, loop;

    // reading data from standard input
    private static void readData() {
        Scanner scanner = new Scanner(System.in);

        height = scanner.nextInt();
        width = scanner.nextInt();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            String row = scanner.nextLine();
            map[i] = row.toCharArray();
        }

        scanner.close();
    }

    // searching for teleports
    private static void findTeleports() {
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                if (map[i][j] == 'T') {
                    if (teleport1 == null) {
                        teleport1 = new int[2];
                        teleport1[0] = i;
                        teleport1[1] = j;
                    } else if (teleport2 == null) {
                        teleport2 = new int[2];
                        teleport2[0] = i;
                        teleport2[1] = j;
                        break;
                    }
                }
            }
        }
    }

    // finding starting position
    private static void findStartingLocation() {
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                if (map[i][j] == '@') {
                    currentY = i;
                    currentX = j;
                    break;
                }
            }
        }
    }

    // simulation
    private static void simulate() {
        // data init
        currentDirection = "SOUTH";
        priorities = new ArrayList<>(Arrays.asList("SOUTH", "EAST", "NORTH", "WEST"));
        positionsHistory = new ArrayList<>();
        loop = new ArrayList<>();
        looped = mapModified = suicideBoothReached = breakerMode = loopIsChecked = false;

        findStartingLocation();
        findTeleports();

        Position nextPosition;
        int nextY, nextX, newX, newY;
        char nextField, newField;

        // main loop
        while (!looped && !suicideBoothReached) {
            // movement simulation
            nextY = currentY;
            nextX = currentX;
            moveAllowed = true;
            directionChanged = false;

            switch (currentDirection) {
                case "SOUTH":
                    nextY++;
                    break;

                case "EAST":
                    nextX++;
                    break;

                case "NORTH":
                    nextY--;
                    break;

                case "WEST":
                    nextX--;
                    break;
            }

            // interaction with map
            nextField = map[nextY][nextX];

            switch (nextField) {
                // indestructible obstacle
                case '#':
                    moveAllowed = false;
                    for (String dir : priorities) {
                        newX = currentX;
                        newY = currentY;

                        switch (dir) {
                            case "SOUTH":
                                newY++;
                                break;

                            case "EAST":
                                newX++;
                                break;

                            case "NORTH":
                                newY--;
                                break;

                            case "WEST":
                                newX--;
                                break;
                        }

                        newField = map[newY][newX];
                        if ((newField != '#' && newField != 'X') || (newField == 'X' && breakerMode)) {
                            nextDirection = dir;
                            directionChanged = true;
                            break;
                        }
                    }
                    break;

                // destructible obstacle
                case 'X':
                    if (breakerMode) {
                        map[nextY][nextX] = ' ';
                        mapModified = true;
                        currentX = nextX;
                        currentY = nextY;
                    } else {
                        moveAllowed = false;
                        for (String dir : priorities) {
                            newX = currentX;
                            newY = currentY;

                            switch (dir) {
                                case "SOUTH":
                                    newY++;
                                    break;

                                case "EAST":
                                    newX++;
                                    break;

                                case "NORTH":
                                    newY--;
                                    break;

                                case "WEST":
                                    newX--;
                                    break;
                            }

                            newField = map[newY][newX];
                            if ((newField != '#' && newField != 'X')) {
                                nextDirection = dir;
                                directionChanged = true;
                                break;
                            }
                        }
                        break;
                    }
                    break;

                // suicide booth
                case '$':
                    currentX = nextX;
                    currentY = nextY;
                    suicideBoothReached = true;
                    break;

                // change direction to SOUTH
                case 'S':
                    currentX = nextX;
                    currentY = nextY;
                    nextDirection = "SOUTH";
                    directionChanged = true;
                    break;

                // change direction to EAST
                case 'E':
                    currentX = nextX;
                    currentY = nextY;
                    nextDirection = "EAST";
                    directionChanged = true;
                    break;

                // change direction to NORTH
                case 'N':
                    currentX = nextX;
                    currentY = nextY;
                    nextDirection = "NORTH";
                    directionChanged = true;
                    break;

                // change direction to WEST
                case 'W':
                    currentX = nextX;
                    currentY = nextY;
                    nextDirection = "WEST";
                    directionChanged = true;
                    break;

                // bear
                case 'B':
                    breakerMode = !breakerMode;
                    currentX = nextX;
                    currentY = nextY;
                    break;

                // inverter
                case 'I':
                    Collections.reverse(priorities);
                    mapModified = true;
                    currentX = nextX;
                    currentY = nextY;
                    break;

                // teleport
                case 'T':
                    if (nextY == teleport1[0] && nextX == teleport1[1]) {
                        currentY = teleport2[0];
                        currentX = teleport2[1];
                    } else {
                        currentY = teleport1[0];
                        currentX = teleport1[1];
                    }
                    break;

                // other fields
                default:
                    currentX = nextX;
                    currentY = nextY;
                    break;
            }

            if (moveAllowed) {
                nextPosition = new Position(currentX, currentY, currentDirection);

                // checking if the robot's route is looped
                if (!loopIsChecked) {
                    if (positionsHistory.contains(nextPosition)) {
                        loopIsChecked = true;
                    }
                } else if (loop.contains(nextPosition)) {
                    if (positionsHistory.containsAll(loop)) {
                        if (!mapModified) {
                            looped = true;
                        } else {
                            mapModified = false;
                            loopIsChecked = false;
                            loop.clear();
                        }
                    } else {
                        loopIsChecked = false;
                        loop.clear();
                    }
                } else {
                    loop.add(nextPosition);
                }

                // adding next position to history
                positionsHistory.add(nextPosition);
            }

            // changing direction
            if (directionChanged) {
                currentDirection = nextDirection;
            }
        }
    }

    // printing answer on standard output
    private static void printAnswer() {
        if (looped) {
            System.out.println("LOOP");
        } else {
            for (Position pos : positionsHistory) {
                System.out.println(pos.getDirection());
            }
        }
    }

    // main loop
    public static void main(String[] args) {
        readData();
        simulate();
        printAnswer();
    }
}