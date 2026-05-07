package Models;

public abstract class Shape {

    protected Point position;

    public Shape(double x, double y) {
        //this.position = new Point(Math.abs(x), Math.abs(y));
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

    protected static String formatDisplayNumber(double value) {
        if (value == (int) value) {
            return String.valueOf((int) value);
        }
        return String.valueOf(value);
    }

    protected static String formatSVGNumber(double value) {
        if (value == (int) value) {
            return String.valueOf((int) value);
        }
        return String.valueOf(value);
    }
}
