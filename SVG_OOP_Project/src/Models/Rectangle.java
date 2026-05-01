package Models;

public class Rectangle extends Shape {

    private double width;
    private double height;
    private String fill;

    public Rectangle(double x, double y, double width, double height, String fill) {
        super(Math.abs(x), Math.abs(y));
        this.width = width;
        this.height = height;
        this.fill = fill;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getFill() {
        return fill;
    }

    public double getA() { return width; }
    public double getB() { return height; }

    @Override
    public boolean isInRegion(Shape region) {
        if (region instanceof Rectangle) {
            Rectangle rect = (Rectangle) region;
            return getX() >= rect.getX() && getX() <= rect.getX() + rect.getWidth()
                && getY() >= rect.getY() && getY() <= rect.getY() + rect.getHeight();
        }

        if (region instanceof Circle) {
            Circle circ = (Circle) region;
            double dx = getX() - circ.getX();
            double dy = getY() - circ.getY();
            return Math.sqrt(dx * dx + dy * dy) <= circ.getRadius();
        }

        return false;
    }

    private boolean pointInCircle(double px, double py, Circle c) {
        double dx = px - c.getX();
        double dy = py - c.getY();
        return dx * dx + dy * dy <= c.getRadius() * c.getRadius();
    }

    @Override
    public String toSVG() {
        return "<rect x=\"" + formatSVGNumber(getX()) + "\" y=\"" + formatSVGNumber(getY()) + "\" width=\"" + formatSVGNumber(width) + "\" height=\"" + formatSVGNumber(height) + "\" fill=\"" + fill + "\" />";
    }

    @Override
    public String toString() {
        return "rectangle " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(width) + " " + formatDisplayNumber(height) + " " + fill;
    }
}
