package Models;

public abstract class Shape {

    protected double x;
    protected double y;

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void translate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    abstract boolean isInRegion(Shape region);
    abstract String toSVG();
}
