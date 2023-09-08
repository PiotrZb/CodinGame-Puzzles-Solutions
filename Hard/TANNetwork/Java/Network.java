import java.util.ArrayList;
import java.util.Collections;

// class representing directed and edge-weighted Graph
class Network {
    private ArrayList<TANStop> stops;

    public Network() {
        this.stops = new ArrayList<>();
    }

    public void addStop(TANStop stop) {
        if (!this.stops.contains(stop) && stop != null) {
            this.stops.add(stop);
        }
    }

    public void addRoute(String stopAId, String stopBId) {
        TANStop stopA = null;
        TANStop stopB = null;
        for (TANStop stop : this.stops) {
            if (stop.getIdentifier().equals(stopAId)) {
                stopA = stop;
            } else if (stop.getIdentifier().equals(stopBId)) {
                stopB = stop;
            }

            if (stopA != null && stopB != null) {
                stopA.addRoute(new Route(stopA, stopB));
                break;
            }
        }
    }

    // implementation of Dijkstra's algorithm
    public ArrayList<String> findShortestRoute(String stopAId, String stopBId) {

        // class representing a row in Dijkstra's table
        class DijkstrasTableField implements Comparable<DijkstrasTableField>{
            public TANStop vertex, previousVertex;
            public double shortestDistance;
            public boolean visited;

            public DijkstrasTableField(TANStop v, TANStop pv, double sd) {
                this.vertex = v;
                this.previousVertex = pv;
                this.shortestDistance = sd;
                this.visited = false;
            }

            @Override
            public int compareTo(DijkstrasTableField o) {
                return Double.compare(this.shortestDistance, o.shortestDistance);
            }
        }

        ArrayList<String> answer = new ArrayList<>();

        if (!stopAId.equals(stopBId)) {
            ArrayList<DijkstrasTableField> table = new ArrayList<>();
            DijkstrasTableField startingVertexData = null;
            DijkstrasTableField endVertexData = null;

            // table init
            for (TANStop stop : this.stops) {
                if (stop.getIdentifier().equals(stopAId)) {
                    startingVertexData = new DijkstrasTableField(stop, null, 0.0);
                    table.add(startingVertexData);
                } else if (stop.getIdentifier().equals(stopBId)) {
                    endVertexData = new DijkstrasTableField(stop, null, Double.MAX_VALUE);
                    table.add(endVertexData);
                } else {
                    table.add(new DijkstrasTableField(stop, null, Double.MAX_VALUE));
                }
            }

            // main logic
            DijkstrasTableField currentVertexData = startingVertexData;
            while (currentVertexData != null) {
                TANStop currentVertex = currentVertexData.vertex;
                double distanceToCurrentVertex = currentVertexData.shortestDistance;
                ArrayList<Route> possibleRoutes = currentVertex.getRoutes();

                for (Route route : possibleRoutes) {
                    double totalDistance = route.getDistance() + distanceToCurrentVertex;
                    DijkstrasTableField nextVertexData = table.stream()
                            .filter(field -> field.vertex == route.getDestination())
                            .findFirst()
                            .get();

                    if (totalDistance < nextVertexData.shortestDistance) {
                        nextVertexData.shortestDistance = totalDistance;
                        nextVertexData.previousVertex = currentVertex;
                    }
                }
                
                // searching for next vertex
                currentVertexData.visited = true;
                currentVertexData = null;
                Collections.sort(table);
                for (DijkstrasTableField field : table){
                    if (!field.visited){
                        currentVertexData = field;
                        break;
                    }
                }
            }

            // final destination not reached
            if (endVertexData.previousVertex == null) {
                answer.add("IMPOSSIBLE");
            }
            // path reconstruction
            else {
                currentVertexData = endVertexData;
                while (!answer.contains(startingVertexData.vertex.getFullName())) {
                    answer.add(currentVertexData.vertex.getFullName());
                    for (DijkstrasTableField vertexData : table) {
                        if (vertexData.vertex == currentVertexData.previousVertex) {
                            currentVertexData = vertexData;
                            break;
                        }
                    }
                }
            }

            Collections.reverse(answer);
        }
        // start stop and end stop are the same
        else {
            for (TANStop stop : this.stops) {
                if (stop.getIdentifier().equals(stopAId)) {
                    answer.add(stop.getFullName());
                }
            }
        }

        return answer;
    }
}