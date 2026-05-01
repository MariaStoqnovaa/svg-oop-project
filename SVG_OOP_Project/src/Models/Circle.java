package Models;

public class Circle extends Shape {

    private double radius;
    private String fill;

    public Circle(double x, double y, double radius, String fill) {
        super(Math.abs(x), Math.abs(y));
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative!");
        }
        this.radius = radius;
        this.fill = fill;
    }

    public double getRadius() {
        return radius;
    }

    public String getFill() {
        return fill;
    }

    @Override
    public boolean isInRegion(Shape region) {
        if (region instanceof Rectangle) {
            Rectangle rect = (Rectangle) region;
            return getX() >= rect.getX() && getX() <= rect.getX() + rect.getWidth()
                && getY() >= rect.getY() && getY() <= rect.getY() + rect.getHeight();
        }

        if (region instanceof Circle) {
            Circle other = (Circle) region;
            double dx = getX() - other.getX();
            double dy = getY() - other.getY();
            return Math.sqrt(dx * dx + dy * dy) <= other.getRadius();
        }

        return false;
    }

    @Override
    public String toSVG() {
        return "<circle cx=\"" + formatSVGNumber(getX()) + "\" cy=\"" + formatSVGNumber(getY()) + "\" r=\"" + formatSVGNumber(radius) + "\" fill=\"" + fill + "\" />";
    }

    @Override
    public String toString() {
        return "circle " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(radius) + " " + fill;
    }
}
