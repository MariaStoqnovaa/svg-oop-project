package Models;

public class Circle extends Shape{

    private double radius;
    private String colorFill;

    public Circle(double x, double y, String colorFill, double radius) {
        super(x, y);

        if(radius < 0)
        {
            throw new IllegalArgumentException("Radius can't be null");
        }

        this.colorFill = colorFill;
        this.radius = radius;
    }

    public String getColorFill() {
        return colorFill;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    boolean isInRegion(Shape region)
    {
        if(region instanceof Rectangle rect)
        {
            return  x - radius >= rect.getX() &&
                    x + radius <= rect.getX() + rect.getA() &&
                    y - radius >= rect.getY() &&
                    y + radius <= rect.getY() + rect.getB();
        }
        if(region instanceof Circle circle)
        {
            double dx = x - circle.getX();
            double dy = y - circle.getY();
            double d = Math.sqrt(dx * dx + dy * dy);
            return d + radius <= circle.getRadius();
        }
        return false;
    }

    @Override
    String toSVG()
    {
        return "<circle center X= " + x + ",center Y= " + y + ",radius= " + radius + ",color fill = " + colorFill;
    }
}
