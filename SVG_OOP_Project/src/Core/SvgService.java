package Core;

import Models.Shape;
import Models.Circle;
import Models.Rectangle;
import Models.Line;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SvgService {

    public void parse(String fileContent, IShapeRepository repo) {
        repo.clear();
        parseRectangles(fileContent, repo);
        parseCircles(fileContent, repo);
        parseLines(fileContent, repo);
    }

    private void parseRectangles(String content, IShapeRepository repo) {
        String pattern = "<rect x=\"([^\"]+)\" y=\"([^\"]+)\" width=\"([^\"]+)\" height=\"([^\"]+)\" fill=\"([^\"]+)\"";
        Matcher m = Pattern.compile(pattern).matcher(content);
        while (m.find()) {
            double x      = Double.parseDouble(m.group(1));
            double y      = Double.parseDouble(m.group(2));
            double width  = Double.parseDouble(m.group(3));
            double height = Double.parseDouble(m.group(4));
            String fill   = m.group(5);
            repo.addShape(new Rectangle(x, y, width, height, fill));
        }
    }

    private void parseCircles(String content, IShapeRepository repo) {
        String pattern = "<circle cx=\"([^\"]+)\" cy=\"([^\"]+)\" r=\"([^\"]+)\" fill=\"([^\"]+)\"";
        Matcher m = Pattern.compile(pattern).matcher(content);
        while (m.find()) {
            double cx   = Double.parseDouble(m.group(1));
            double cy   = Double.parseDouble(m.group(2));
            double r    = Double.parseDouble(m.group(3));
            String fill = m.group(4);
            repo.addShape(new Circle(cx, cy, r, fill));
        }
    }

    private void parseLines(String content, IShapeRepository repo) {
        String pattern = "<line x1=\"([^\"]+)\" y1=\"([^\"]+)\" x2=\"([^\"]+)\" y2=\"([^\"]+)\" stroke=\"([^\"]+)\"";
        Matcher m = Pattern.compile(pattern).matcher(content);
        while (m.find()) {
            double x1     = Double.parseDouble(m.group(1));
            double y1     = Double.parseDouble(m.group(2));
            double x2     = Double.parseDouble(m.group(3));
            double y2     = Double.parseDouble(m.group(4));
            String stroke = m.group(5);
            repo.addShape(new Line(x1, y1, x2, y2, stroke));
        }
    }

    public String serialize(IShapeRepository repo) {
        StringBuilder svgContent = new StringBuilder();
        svgContent.append("<?xml version=\"1.0\" standalone=\"no\"?>\n");
        svgContent.append("<svg>\n");

        for (Shape shape : repo.getAll()) {
            svgContent.append("  ").append(shape.toSVG()).append("\n");
        }

        svgContent.append("</svg>");
        return svgContent.toString();
    }
}
