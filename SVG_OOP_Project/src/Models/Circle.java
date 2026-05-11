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
     * Tests whether this circle lies fully within the given region.
     * For a rectangular region the axis-aligned bounding box of this circle
     * must fit inside the rectangle; for a circular region this circle must
     * fit inside the other (distance between centers plus this radius must
     * not exceed the other radius).
     *
     * @param region a {@link Rectangle} or {@link Circle} representing the region
     * @return {@code true} if this circle is entirely contained in the region
     */
    @Override
    public boolean isInRegion(Shape region) {
        if (region instanceof Rectangle r ) {
            return getX() - radius >= r.getX()
                    && getX() + radius <= r.getX() + r.getWidth()
                    && getY() - radius >= r.getY()
                    && getY() + radius <= r.getY() + r.getHeight();
        }

        if (region instanceof Circle other) {
            double dx = getX() - other.getX();
            double dy = getY() - other.getY();
            double centerDist = Math.sqrt(dx * dx + dy * dy);
            return centerDist + radius <= other.getRadius();
        }

        return false;

    }
    /**
     * @return an SVG {@code <circle>} element string,
     *         e.g. {@code <circle cx="10" cy="10" r="5" fill="blue" />}
     */
    @Override
    public String toSVG() {
        return "<circle cx=\"" + formatSVGNumber(getX()) + "\" cy=\"" + formatSVGNumber(getY()) + "\" r=\"" + formatSVGNumber(radius) + "\" fill=\"" + fill + "\" />";
    }

    /**
     * @return a human-readable description, e.g. {@code circle 10 10 5 blue}
     */
    @Override
    public String toString() {
        return "circle " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(radius) + " " + fill;
    }
}
