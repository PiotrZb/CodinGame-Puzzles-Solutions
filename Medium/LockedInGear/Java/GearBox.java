import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// class representing graph
class GearBox{
    
    private final ArrayList<Gear> gears;
    private Gear drivenGear, lastGear;

    public GearBox(){
        this.drivenGear = null;
        this.lastGear = null;
        this.gears = new ArrayList<>();
    }

    public void addGear(int x, int y, int r){
        if (drivenGear == null){
            this.drivenGear = new Gear(x, y, r);
            this.gears.add(drivenGear);
            this.drivenGear.setDirection("CW");
        } else{
            this.lastGear = new Gear(x, y, r);
            this.gears.add(lastGear);
        }
    }

    private void generateConnections(){
        Queue<Gear> gearsToCheck = new LinkedList<>(this.gears);
        while (!gearsToCheck.isEmpty()){
            Gear currentGear = gearsToCheck.poll();
            for (Gear g : this.gears){
                // checking distance between centers
                int a = Math.abs(currentGear.getX() - g.getX());
                int b = Math.abs(currentGear.getY() - g.getY());
                int c = currentGear.getR() + g.getR();

                if ((a*a) + (b*b) == c*c){
                    currentGear.addConnectedGear(g);
                    g.addConnectedGear(currentGear);
                }
            }
        }
    }

    public String solve(){
        this.generateConnections();

        // BFS
        Queue<Gear> queue = new LinkedList<>();
        queue.add(this.drivenGear);
        this.drivenGear.setAsVisited();
        boolean jammed = false;

        while (!queue.isEmpty() && !jammed){
            Gear currentGear = queue.poll();

            for (Gear g : currentGear.getConnectedGears()){
                if (!g.isVisited()){
                    queue.add(g);
                    g.setAsVisited();
                }

                if (g.getDirection().equals("NOT MOVING")){
                    if (currentGear.getDirection().equals("CW")){
                        g.setDirection("CCW");
                    } else if (currentGear.getDirection().equals("CCW")) {
                        g.setDirection("CW");
                    }
                } else if (currentGear.getDirection().equals(g.getDirection())){
                    jammed = true;
                    this.lastGear.setDirection("NOT MOVING");
                    break;
                }
            }
        }

        return this.lastGear.getDirection();
    }
}