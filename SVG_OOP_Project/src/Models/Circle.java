package Models;

public class Circle extends Shape {

    private double radius;
    private String fill;

    public Circle(double x, double y, double radius, String fill) {
        super(x, y);
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative!");
        }
        this.radius = radius;
        this.fill = fill;
    }

    public double getRadius() {
        return radius;
    }

    public String getFill() {
        return fill;
    }

    @Override
    public boolean isInRegion(Shape region) {
        if (region instanceof Rectangle) {
            Rectangle rect = (Rectangle) region;
            boolean leftOk  = getX() - radius >= rect.getX();
            boolean rightOk = getX() + radius <= rect.getX() + rect.getWidth();
            boolean topOk   = getY() - radius >= rect.getY();
            boolean botOk   = getY() + radius <= rect.getY() + rect.getHeight();
            return leftOk && rightOk && topOk && botOk;
        }

        if (region instanceof Circle) {
            Circle other = (Circle) region;
            double dx = getX() - other.getX();
            double dy = getY() - other.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            return distance + radius <= other.getRadius();
        }

        return false;
    }

    @Override
    public String toSVG() {
        return String.format("<circle cx=\"%.1f\" cy=\"%.1f\" r=\"%.1f\" fill=\"%s\" />",
                getX(), getY(), radius, fill);
    }

    @Override
    public String toString() {
        return String.format("circle %.1f %.1f %.1f %s", getX(), getY(), radius, fill);
    }
}
