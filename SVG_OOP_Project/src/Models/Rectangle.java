package Models;

public class Rectangle extends Shape {

    private double width;
    private double height;
    private String fill; // color

    public Rectangle(double x, double y, double width, double height, String fill) {
        super(x, y);
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
            boolean leftOk  = getX() >= rect.getX();
            boolean topOk   = getY() >= rect.getY();
            boolean rightOk = (getX() + width)  <= (rect.getX() + rect.getWidth());
            boolean botOk   = (getY() + height) <= (rect.getY() + rect.getHeight());
            return leftOk && topOk && rightOk && botOk;
        }

        if (region instanceof Circle) {
            Circle circ = (Circle) region;

            return pointInCircle(getX(),         getY(),          circ)
                && pointInCircle(getX() + width, getY(),          circ)
                && pointInCircle(getX(),         getY() + height, circ)
                && pointInCircle(getX() + width, getY() + height, circ);
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
        return String.format("<rect x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" height=\"%.1f\" fill=\"%s\" />",
                getX(), getY(), width, height, fill);
    }

    @Override
    public String toString() {
        return String.format("rectangle %.1f %.1f %.1f %.1f %s", getX(), getY(), width, height, fill);
    }
}
