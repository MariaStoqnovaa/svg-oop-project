package Models;

public class Line extends Shape {

    private Point endPoint;
    private String stroke;

    /**
     * Creates a line between the given endpoints.
     *
     * @param x1     X coordinate of the first endpoint
     * @param y1     Y coordinate of the first endpoint
     * @param x2     X coordinate of the second endpoint
     * @param y2     Y coordinate of the second endpoint
     * @param stroke SVG stroke color (e.g. {@code "black"} or {@code "#123456"})
     */
    public Line(double x1, double y1, double x2, double y2, String stroke) {
        //super(Math.abs(x1), Math.abs(y1));
        super(x1, y1);
        this.endPoint = new Point(x2, y2);
        this.stroke = stroke;
    }

    /** @return the X coordinate of the second endpoint */
    public double getX2() {
        return endPoint.getX();
    }

    /** @return the Y coordinate of the second endpoint */
    public double getY2() {
        return endPoint.getY();
    }

    /** @return the SVG stroke color of this line */
    public String getStroke() {
        return stroke;
    }

    /**
     * Moves both endpoints of this line by the given offsets so the
     * segment retains its length and direction.
     *
     * @param horizontal how far to move along the X axis (positive = right)
     * @param vertical   how far to move along the Y axis (positive = down)
     */
    @Override
    public void translate(double horizontal, double vertical) {
        super.translate(horizontal, vertical);
        endPoint.translate(horizontal, vertical);
    }

    /**
     * Tests whether this line segment lies fully within the given region.
     * Both endpoints must be inside the region; since rectangles and
     * circles are convex, the entire segment then lies inside as well.
     *
     * @param region a {@link Rectangle} or {@link Circle} representing the region
     * @return {@code true} if both endpoints are inside the region
     */
    @Override
    public boolean isInRegion(Shape region) {
        if (region instanceof Rectangle r) {

            return pointInRectangle(getX(),  getY(),  r)
                    && pointInRectangle(getX2(), getY2(), r);
        }

        if (region instanceof Circle c) {
            double r2 = c.getRadius() * c.getRadius();
            return pointInCircle(getX(),  getY(),  c, r2)
                    && pointInCircle(getX2(), getY2(), c, r2);
        }

        return false;
    }

    private boolean pointInRectangle(double px, double py, Rectangle r) {
        return px >= r.getX() && px <= r.getX() + r.getWidth()
                && py >= r.getY() && py <= r.getY() + r.getHeight();
    }

    private boolean pointInCircle(double px, double py, Circle c, double r2) {
        double dx = px - c.getX();
        double dy = py - c.getY();
        return dx * dx + dy * dy <= r2;
    }

    /**
     * @return an SVG {@code <line>} element string,
     *         e.g. {@code <line x1="0" y1="0" x2="10" y2="10" stroke="black" />}
     */
    @Override
    public String toSVG() {
        return "<line x1=\"" + formatSVGNumber(getX()) + "\" y1=\"" + formatSVGNumber(getY()) + "\" x2=\"" + formatSVGNumber(getX2()) + "\" y2=\"" + formatSVGNumber(getY2()) + "\" stroke=\"" + stroke + "\" />";
    }

    /**
     * @return a human-readable description, e.g. {@code line 0 0 10 10 black}
     */
    @Override
    public String toString() {
        return "line " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(getX2()) + " " + formatDisplayNumber(getY2()) + " " + stroke;
    }
}
