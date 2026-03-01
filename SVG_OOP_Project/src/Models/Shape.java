package Models;

public abstract class Shape {

    protected int x;
    protected int y;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void translate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract boolean isInRegion(Shape region);
    abstract String toSVG();
}
