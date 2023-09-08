import java.util.ArrayList;

// class representing vertex
class TANStop {
    private final String identifier, fullName;
    private final double latitude, longitude;
    private ArrayList<Route> routes;

    public TANStop(String id, String name, double lat, double lng) {
        this.identifier = id;
        this.fullName = name;
        this.latitude = lat;
        this.longitude = lng;
        this.routes = new ArrayList<>();
    }

    public void addRoute(Route route) {
        if (route != null) {
            this.routes.add(route);
        }
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getFullName() {
        return this.fullName;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public ArrayList<Route> getRoutes() {
        return this.routes;
    }
}