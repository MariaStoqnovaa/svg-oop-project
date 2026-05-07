package Models;

public class Line extends Shape {

    private Point endPoint;
    private String stroke;

    public Line(double x1, double y1, double x2, double y2, String stroke) {
        //super(Math.abs(x1), Math.abs(y1));
        super(x1, y1);
        this.endPoint = new Point(x2, y2);
        this.stroke = stroke;
    }

    public double getX2() {
        return endPoint.getX();
    }

    public double getY2() {
        return endPoint.getY();
    }

    public String getStroke() {
        return stroke;
    }

    @Override
    public void translate(double horizontal, double vertical) {
        super.translate(horizontal, vertical);
        endPoint.translate(horizontal, vertical);
    }

    @Override
    public boolean isInRegion(Shape region) {
        if (region instanceof Rectangle r) {
            // Both endpoints must lie inside the rectangle.
            // (A line segment is convex, so if both endpoints are inside
            // a convex region, the whole segment is inside.)
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

    @Override
    public String toSVG() {
        return "<line x1=\"" + formatSVGNumber(getX()) + "\" y1=\"" + formatSVGNumber(getY()) + "\" x2=\"" + formatSVGNumber(getX2()) + "\" y2=\"" + formatSVGNumber(getY2()) + "\" stroke=\"" + stroke + "\" />";
    }

    @Override
    public String toString() {
        return "line " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(getX2()) + " " + formatDisplayNumber(getY2()) + " " + stroke;
    }
}
