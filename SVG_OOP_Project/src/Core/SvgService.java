package Core;

import Models.Circle;
import Models.Rectangle;
import Models.Line;
import Models.Shape;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class SvgService {


    public void parse(String fileContent, IShapeRepository repo) {
        repo.clear();
        findAllShapes(fileContent, repo);
    }

    private String getValue(String tag, String attribute) {
        String key = attribute + "=\"";
        int start = tag.indexOf(key);

        if (start == -1) {
            return "";
        }

        start += key.length();
        int end = tag.indexOf("\"", start);

        if (end == -1) {
            return "";
        }

        return tag.substring(start, end);
    }

    private double getDoubleValue(String tag, String attribute) {
        return Double.parseDouble(getValue(tag, attribute));
    }

    private void findAllShapes(String svgText, IShapeRepository repo) {
        int position = 0;

        while (true) {
            int rectStart = svgText.indexOf("<rect", position);
            int circleStart = svgText.indexOf("<circle", position);
            int lineStart = svgText.indexOf("<line", position);

            int start = -1;
            String tagName = null;

            if (rectStart != -1 && (start == -1 || rectStart < start)) {
                start = rectStart;
                tagName = "rect";
            }

            if (circleStart != -1 && (start == -1 || circleStart < start)) {
                start = circleStart;
                tagName = "circle";
            }

            if (lineStart != -1 && (start == -1 || lineStart < start))
            {
                start = lineStart;
                tagName = "line";
            }

            if (start == -1)
            {
                break;
            }

            int end = svgText.indexOf(">", start);
            if (end == -1)
            {
                break;
            }

            String tag = svgText.substring(start, end + 1);
            position = end + 1;

            if (tagName.equals("rect"))
            {
                double x = getDoubleValue(tag, "x");
                double y = getDoubleValue(tag, "y");
                double width = getDoubleValue(tag, "width");
                double height = getDoubleValue(tag, "height");
                String color = getValue(tag, "fill");

                repo.addShape(new Rectangle(x, y, width, height, color));
            }
            else if (tagName.equals("circle"))
            {
                double cx = getDoubleValue(tag, "cx");
                double cy = getDoubleValue(tag, "cy");
                double r = getDoubleValue(tag, "r");
                String color = getValue(tag, "fill");

                repo.addShape(new Circle(cx, cy, r, color));
            }
            else if (tagName.equals("line"))
            {
                double x1 = getDoubleValue(tag, "x1");
                double y1 = getDoubleValue(tag, "y1");
                double x2 = getDoubleValue(tag, "x2");
                double y2 = getDoubleValue(tag, "y2");
                String color = getValue(tag, "stroke");

                repo.addShape(new Line(x1, y1, x2, y2, color));
            }
        }
    }
    public String serialize(IShapeRepository repo) {
        StringBuilder svgContent = new StringBuilder();

        svgContent.append("<?xml version=\"1.0\" standalone=\"no\"?>\n");
        svgContent.append("<svg xmlns=\"http://www.w3.org/2000/svg\" ");
        svgContent.append("width=\"400\" height=\"300\" viewBox=\"0 0 400 300\">\n");

        for (Shape shape : repo.getAll()) {
            svgContent.append("    ").append(shape.toSVG()).append("\n");
        }

        svgContent.append("</svg>");
        return svgContent.toString();
    }
}