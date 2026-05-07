package Models;

public class Circle extends Shape {

    private double radius;
    private String fill;

    public Circle(double x, double y, double radius, String fill) {
        //super(Math.abs(x), Math.abs(y));
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
        if (region instanceof Rectangle r ) {
            // The bounding box of this circle must fit inside the rectangle
            return getX() - radius >= r.getX()
                    && getX() + radius <= r.getX() + r.getWidth()
                    && getY() - radius >= r.getY()
                    && getY() + radius <= r.getY() + r.getHeight();
        }

        if (region instanceof Circle other) {
            double dx = getX() - other.getX();
            double dy = getY() - other.getY();
            double centerDist = Math.sqrt(dx * dx + dy * dy);
            // This circle fits inside the other if the distance between centers
            // plus this radius doesn't exceed the other radius
            return centerDist + radius <= other.getRadius();
        }

        return false;

    }

    @Override
    public String toSVG() {
        return "<circle cx=\"" + formatSVGNumber(getX()) + "\" cy=\"" + formatSVGNumber(getY()) + "\" r=\"" + formatSVGNumber(radius) + "\" fill=\"" + fill + "\" />";
    }

    @Override
    public String toString() {
        return "circle " + formatDisplayNumber(getX()) + " " + formatDisplayNumber(getY()) + " " + formatDisplayNumber(radius) + " " + fill;
    }
}
