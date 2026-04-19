package Models;

public class Line extends Shape {

    private Point endPoint;
    private String stroke;

    public Line(double x1, double y1, double x2, double y2, String stroke) {
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
        if (region instanceof Rectangle) {
            Rectangle rect = (Rectangle) region;

            boolean startInside = getX() >= rect.getX()
                    && getX() <= rect.getX() + rect.getWidth()
                    && getY() >= rect.getY()
                    && getY() <= rect.getY() + rect.getHeight();

            boolean endInside = getX2() >= rect.getX()
                    && getX2() <= rect.getX() + rect.getWidth()
                    && getY2() >= rect.getY()
                    && getY2() <= rect.getY() + rect.getHeight();

            return startInside && endInside;
        }

        if (region instanceof Circle) {
            Circle circ = (Circle) region;
            return pointInCircle(getX(), getY(), circ)
                && pointInCircle(getX2(), getY2(), circ);
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
        return String.format("<line x1=\"%.1f\" y1=\"%.1f\" x2=\"%.1f\" y2=\"%.1f\" stroke=\"%s\" />",
                getX(), getY(), getX2(), getY2(), stroke);
    }

    @Override
    public String toString() {
        return String.format("line %.1f %.1f %.1f %.1f %s", getX(), getY(), getX2(), getY2(), stroke);
    }
}
