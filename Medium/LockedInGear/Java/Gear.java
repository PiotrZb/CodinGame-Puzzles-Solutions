import java.util.ArrayList;

// class representing single vertex
class Gear{
    
    private final int x,y,r;
    private final ArrayList<Gear> connectedGears;
    private String direction;
    private boolean visited;

    public Gear(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
        this.direction = "NOT MOVING";
        this.connectedGears = new ArrayList<>();
        this.visited = false;
    }

    public void setDirection(String dir){
        if (dir.equals("NOT MOVING") || dir.equals("CCW") || dir.equals("CW")) {
            this.direction = dir;
        }
    }

    public void addConnectedGear(Gear gear){
        if (gear != null){
            this.connectedGears.add(gear);
        }
    }

    public void setAsVisited(){
        this.visited = true;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public ArrayList<Gear> getConnectedGears(){
        return this.connectedGears;
    }

    public String getDirection(){
        return this.direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getR() {
        return this.r;
    }
}