package Models;

/**
 * Represents an SVG rectangle defined by position, width, height, and fill
 * color.
 */

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

    /** @return alias for the rectangle width */
    public double getA() { return width; }

    /** @return alias for the rectangle height */
    public double getB() { return height; }

    /**
     * Checks whether the rectangle lies inside the given region.
     *
     * @param region rectangle or circle region
     * @return {@code true} if the rectangle is fully inside the region
     */

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
        return region.isContainsRec(this);
    }

    @Override
    public boolean isContainsRec(Rectangle rectangle) {
        double left = rectangle.getX();
        double top = rectangle.getY();
        double right = rectangle.getX() + rectangle.getWidth();
        double bottom = rectangle.getY() + rectangle.getHeight();

        return left >= this.getX()
                && right <= this.getX() + this.getWidth()
                && top >= this.getY()
                && bottom <= this.getY() + this.getHeight();
    }

    @Override
    public boolean isContainsLine(Line line) {
        return isPointInRectangle(line.getX(), line.getY())
                && isPointInRectangle(line.getX2(), line.getY2());
    }

    @Override
    public boolean isContainsCircle(Circle circle) {
        double cx = circle.getX();
        double cy = circle.getY();
        double r = circle.getRadius();
        return cx - r >= this.getX()
                && cy - r >= this.getY()
                && cx + r <= this.getX() + this.getWidth()
                && cy + r <= this.getY() + this.getHeight();
    }

    private boolean isPointInRectangle(double px, double py)
    {
        return px >= this.getX() && px <= this.getX() + this.width
                && py >= this.getY() && py <= this.getY() + this.height;
    }

    /**
     * @return an SVG {@code <rect>} element string,
     *         e.g. {@code <rect x="5" y="5" width="10" height="10" fill="green" />}
     */

    /**
     * Returns the SVG representation of the rectangle.
     *
     * @return SVG {@code <rect>} element string
     */
    @Override
    public String toSVG() {
        return "<rect x=\"" + formatSVGNumber(getX())
                + "\" y=\"" + formatSVGNumber(getY())
                + "\" width=\"" + formatSVGNumber(width)
                + "\" height=\"" + formatSVGNumber(height)
                + "\" fill=\"" + fill + "\" />";
    }

    /**
     * Returns a text description of the rectangle.
     *
     * @return rectangle description
     */
    @Override
    public String toString() {
        return "rectangle " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(width) + " " + formatDisplayNumber(height) + " " + fill;
    }
}
