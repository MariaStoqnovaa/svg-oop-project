package Models;

public class Rectangle extends Shape {

    private double width;
    private double height;
    private String fill;

    public Rectangle(double x, double y, double width, double height, String fill) {
        //super(Math.abs(x), Math.abs(y));
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
    public boolean isInRegion(Shape region)
    {
        double left   = getX();
        double top    = getY();
        double right  = getX() + width;
        double bottom = getY() + height;

        if (region instanceof Rectangle r) {
            return left   >= r.getX()
                    && right  <= r.getX() + r.getWidth()
                    && top    >= r.getY()
                    && bottom <= r.getY() + r.getHeight();
        }
        if (region instanceof Circle c) {
            // All four corners must be inside the circle.
            // Use squared distance to avoid sqrt.
            double r2 = c.getRadius() * c.getRadius();
            return cornerInCircle(left,  top,    c, r2)
                    && cornerInCircle(right, top,    c, r2)
                    && cornerInCircle(left,  bottom, c, r2)
                    && cornerInCircle(right, bottom, c, r2);
        }

        return false;
    }

    private boolean cornerInCircle(double px, double py, Circle c, double r2) {
        double dx = px - c.getX();
        double dy = py - c.getY();
        return dx * dx + dy * dy <= r2;
    }
//    private boolean pointInCircle(double px, double py, Circle c) {
//        double dx = px - c.getX();
//        double dy = py - c.getY();
//        return dx * dx + dy * dy <= c.getRadius() * c.getRadius();
//    }

    @Override
    public String toSVG() {
        return "<rect x=\"" + formatSVGNumber(getX()) + "\" y=\"" + formatSVGNumber(getY()) + "\" width=\"" + formatSVGNumber(width) + "\" height=\"" + formatSVGNumber(height) + "\" fill=\"" + fill + "\" />";
    }

    @Override
    public String toString() {
        return "rectangle " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(width) + " " + formatDisplayNumber(height) + " " + fill;
    }
}
