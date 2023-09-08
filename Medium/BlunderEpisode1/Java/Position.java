import java.util.Objects;

// class that helps to create moves history
class Position {
    Position(int x, int y, String dir) {
        this.positionX = x;
        this.positionY = y;
        this.direction = dir;
    }

    private int positionX;
    private int positionY;
    private String direction;

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public String getDirection() {
        return this.direction;
    }

    @Override
    public boolean equals(Object obj) {
        Position secondPosition = (Position) obj;
        return this.positionY == secondPosition.getPositionY() && this.positionX == secondPosition.getPositionX() && this.direction.equals(secondPosition.getDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.positionX, this.positionY, this.direction);
    }
}