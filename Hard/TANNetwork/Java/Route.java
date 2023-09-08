// class representing edge
class Route {
    private final TANStop destination;
    private final double distance;

    public Route(TANStop A, TANStop B) {
        this.destination = B;
        // calculating distance between given stops
        double x = (B.getLongitude() - A.getLongitude()) * Math.cos((A.getLatitude() + B.getLatitude()) / 2.0);
        double y = B.getLatitude() - A.getLatitude();
        this.distance = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0)) * 6371.0;
    }

    public TANStop getDestination() {
        return this.destination;
    }

    public double getDistance() {
        return this.distance;
    }
}