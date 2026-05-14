package Models;
/**
 * Represents an SVG line segment defined by two endpoints and a stroke color.
 */
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
     * Moves both endpoints of the line by the given offsets.
     *
     * @param horizontal movement along the x-axis
     * @param vertical movement along the y-axis
     */

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
     * Checks whether the line lies inside the given region.
     *
     * @param region rectangle or circle region
     * @return {@code true} if both endpoints are inside the region
     */
    @Override
    public boolean isInRegion(Shape region) {

        return region.isContainsLine(this);
    }

    @Override
    public boolean isContainsRec(Rectangle rectangle) {
        return false;
    }

    @Override
    public boolean isContainsLine(Line line) {
        return false;
    }

    @Override
    public boolean isContainsCircle(Circle circle) {
        return false;
    }

//    private boolean pointInRectangle(double px, double py, Rectangle r) {
//        return px >= r.getX() && px <= r.getX() + r.getWidth()
//                && py >= r.getY() && py <= r.getY() + r.getHeight();
//    }
//
//    private boolean pointInCircle(double px, double py, Circle c, double r2) {
//        double dx = px - c.getX();
//        double dy = py - c.getY();
//        return dx * dx + dy * dy <= r2;
//    }

    /**
     * Returns the SVG representation of the line.
     *
     * @return SVG {@code <line>} element string
     */
    @Override
    public String toSVG() {
        return "<line x1=\"" + formatSVGNumber(getX()) + "\" y1=\"" + formatSVGNumber(getY()) + "\" x2=\"" + formatSVGNumber(getX2()) + "\" y2=\"" + formatSVGNumber(getY2()) + "\" stroke=\"" + stroke + "\" />";
    }

    /**
     * Returns a text description of the line.
     *
     * @return line description
     */
    @Override
    public String toString() {
        return "line " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(getX2()) + " " + formatDisplayNumber(getY2()) + " " + stroke;
    }
}
