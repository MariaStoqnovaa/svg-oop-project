package Models;

public class Line extends Shape {

    private Point endPoint;
    private String stroke;

    public Line(double x1, double y1, double x2, double y2, String stroke) {
        super(Math.abs(x1), Math.abs(y1));
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
        return "<line x1=\"" + formatSVGNumber(getX()) + "\" y1=\"" + formatSVGNumber(getY()) + "\" x2=\"" + formatSVGNumber(getX2()) + "\" y2=\"" + formatSVGNumber(getY2()) + "\" stroke=\"" + stroke + "\" />";
    }

    @Override
    public String toString() {
        return "line " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(getX2()) + " " + formatDisplayNumber(getY2()) + " " + stroke;
    }
}
