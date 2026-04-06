package Models;

public class Line extends Shape
{
    private double xLine;
    private double yLine;
    private String colorOfLine;

    public Line(double x, double y, String colorOfLine, double xLine, double yLine) {
        super(x, y);
        this.colorOfLine = colorOfLine;
        this.xLine = xLine;
        this.yLine = yLine;
    }

    public String getColorOfLine() {
        return colorOfLine;
    }

    public double getxLine() {
        return xLine;
    }

    public double getyLine() {
        return yLine;
    }

    @Override
    boolean isInRegion(Shape region)
    {
        if(region instanceof Rectangle rect)
        {
            return x >= rect.getX() &&
                    x <= rect.getX() + rect.getA() &&
                    y >= rect.getY() &&
                    y <= rect.getY() + rect.getB() &&
                    xLine >= rect.getX() &&
                    xLine <= rect.getX() + rect.getA() &&
                    yLine >= rect.getY() &&
                    yLine <= rect.getY() + rect.getB();
        }
        else if(region instanceof Circle circ)
        {
            return circleIsPoint(x,y,circ) &&
                    circleIsPoint(xLine,yLine,circ);
        }

        return false;
    }

    private boolean circleIsPoint(double px,double py,Circle c)
    {
        double dx = px - c.getX();
        double dy = py - c.getY();
        return dx * dx + dy * dy <= c.getRadius() * c.getRadius();
    }

    @Override
    String toSVG()
    {
        return "<line center X= " + xLine + ",center Y= " + yLine + ",x2 = " + x + "y2 = " + y + ",color of line = " + colorOfLine;
    }
}
