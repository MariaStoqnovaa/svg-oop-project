package Models;


/**
 * SVG rectangle defined by its top-left anchor point, width, height
 * and fill color. Containment in a region is decided by checking
 * whether all four corners lie inside the region.
 */
public class Rectangle extends Shape {

    private double width;
    private double height;
    private String fill;

    /**
     * Creates a rectangle with the given top-left corner, dimensions and fill.
     *
     * @param x      X coordinate of the top-left corner
     * @param y      Y coordinate of the top-left corner
     * @param width  width of the rectangle
     * @param height height of the rectangle
     * @param fill   SVG fill color (e.g. {@code "red"} or {@code "#00ff00"})
     */
    public Rectangle(double x, double y, double width, double height, String fill) {
        //super(Math.abs(x), Math.abs(y));
        super(x, y);
        this.width = width;
        this.height = height;
        this.fill = fill;
    }


    /** @return the width of this rectangle */
    public double getWidth() {
        return width;
    }

    /** @return the height of this rectangle */
    public double getHeight() {
        return height;
    }


    /** @return the SVG fill color of this rectangle */
    public String getFill() {
        return fill;
    }

    /** @return alias for {@link #getWidth()} (side A of the rectangle) */
    public double getA() { return width; }

    /** @return alias for {@link #getHeight()} (side B of the rectangle) */
    public double getB() { return height; }

    /**
     * Tests whether this rectangle lies fully within the given region.
     * For a rectangular region all four corners must be inside its bounds;
     * for a circular region all four corners must lie within the circle.
     *
     * @param region a {@link Rectangle} or {@link Circle} representing the region
     * @return {@code true} if every corner of this rectangle is inside the region
     */
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

    /**
     * @return an SVG {@code <rect>} element string,
     *         e.g. {@code <rect x="5" y="5" width="10" height="10" fill="green" />}
     */
    @Override
    public String toSVG() {
        return "<rect x=\"" + formatSVGNumber(getX()) + "\" y=\"" + formatSVGNumber(getY()) + "\" width=\"" + formatSVGNumber(width) + "\" height=\"" + formatSVGNumber(height) + "\" fill=\"" + fill + "\" />";
    }

    /**
     * @return a human-readable description, e.g. {@code rectangle 5 5 10 10 green}
     */
    @Override
    public String toString() {
        return "rectangle " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(width) + " " + formatDisplayNumber(height) + " " + fill;
    }
}
