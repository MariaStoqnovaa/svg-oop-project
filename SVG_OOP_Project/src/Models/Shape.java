package Models;

public abstract class Shape {

    protected Point position;

    public Shape(double x, double y) {
        this.position = new Point(x, y);
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public void translate(double horizontal, double vertical) {
        position.translate(horizontal, vertical);
    }

    public abstract boolean isInRegion(Shape region);

    public abstract String toSVG();

    public abstract String toString();
}
