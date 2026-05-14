package Models;

/**
 * SVG circle defined by its center, radius and fill color. The center
 * is stored as the inherited {@link Shape#position} anchor.
 */
public class Circle extends Shape {

    private double radius;
    private String fill;

    /**
     * Creates a circle with the given center, radius and fill.
     *
     * @param x      X coordinate of the center
     * @param y      Y coordinate of the center
     * @param radius radius of the circle; must not be negative
     * @param fill   SVG fill color (e.g. {@code "blue"} or {@code "#abcdef"})
     * @throws IllegalArgumentException if {@code radius} is negative
     */
    public Circle(double x, double y, double radius, String fill) {
        //super(Math.abs(x), Math.abs(y));
        super(x, y);
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative!");
        }
        this.radius = radius;
        this.fill = fill;
    }


    /** @return the radius of this circle */
    public double getRadius() {
        return radius;
    }

    /** @return the SVG fill color of this circle */
    public String getFill() {
        return fill;
    }

    /**
     * Checks whether the circle lies inside the given region.
     *
     * @param region rectangle or circle region
     * @return {@code true} if the circle is fully inside the region
     */
    @Override
    public boolean isInRegion(Shape region)
    {
        return region.isContainsCircle(this);
    }
    /**
     * Tests whether this circle contains a rectangle.
     * All four corners of the rectangle must be inside this circle.
     *
     * @param rectangle the rectangle to test
     * @return {@code true} if the rectangle is fully inside this circle
     */
    @Override
    public boolean isContainsRec(Rectangle rectangle) {
        double left = rectangle.getX();
        double top = rectangle.getY();
        double right = rectangle.getX() + rectangle.getWidth();
        double bottom = rectangle.getY() + rectangle.getHeight();

        double r2 = this.radius * this.radius;
        return isPointInCircle(left,  top,    r2)
                && isPointInCircle(right, top,    r2)
                && isPointInCircle(left,  bottom, r2)
                && isPointInCircle(right, bottom, r2);
    }

    /**
     * Tests whether this circle contains a line.
     * Both endpoints of the line must be inside this circle.
     *
     * @param line the line to test
     * @return {@code true} if both endpoints are inside this circle
     */
    @Override
    public boolean isContainsLine(Line line) {
        double r2 = this.radius * this.radius;
        return isPointInCircle(line.getX() , line.getY() ,r2)
                && isPointInCircle(line.getX2(),line.getY2(),r2);
    }

    @Override
    public boolean isContainsCircle(Circle circle) {
        double dx = circle.getX() - this.getX();
        double dy = circle.getY() - this.getY();
        double distance = Math.sqrt(dx*dx+dy*dy);
        return distance + circle.getRadius() <= this.radius;
    }

    /**
     * Helper method to test if a point is inside this circle.
     *
     * @param px X coordinate of the point
     * @param py Y coordinate of the point
     * @param r2 squared radius (optimization to avoid repeated multiplication)
     * @return {@code true} if the point is inside this circle
     */
    private boolean isPointInCircle(double px, double py, double r2)
    {
        double dx = px - this.getX();
        double dy = py - this.getY();
        return  dx * dx + dy * dy <= r2;
    }
    /**
     * Returns the SVG representation of the circle.
     *
     * @return SVG {@code <circle>} element string
     */
    @Override
    public String toSVG() {
        return "<circle cx=\"" + formatSVGNumber(getX()) + "\" cy=\"" + formatSVGNumber(getY()) + "\" r=\"" + formatSVGNumber(radius) + "\" fill=\"" + fill + "\" />";
    }

    /**
     * Returns a text description of the circle.
     *
     * @return circle description
     */
    @Override
    public String toString() {
        return "circle " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(radius) + " " + fill;
    }
}
