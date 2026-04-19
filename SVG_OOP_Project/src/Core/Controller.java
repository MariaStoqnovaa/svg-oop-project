package Core;

import Core.Commands.implementation.close.CloseCommand;
import Core.Commands.implementation.edit.CreateCommand;
import Core.Commands.implementation.edit.EraseCommand;
import Core.Commands.implementation.edit.TranslateCommand;
import Core.Commands.implementation.edit.WithinCommand;
import Core.Commands.implementation.exit.ExitCommand;
import Core.Commands.implementation.help.HelpCommand;
import Core.Commands.implementation.open.OpenCommand;
import Core.Commands.implementation.print.PrintCommand;
import Core.Commands.implementation.save.SaveCommand;
import Core.Commands.implementation.saveAs.SaveAsCommand;
import Models.Circle;
import Models.Line;
import Models.Rectangle;
import Models.Shape;

public class Controller {

    private ShapeRepository repository = new ShapeRepository();

    public String execute(String input) {
        String[] parts = input.trim().split("\\s+");

        if (parts.length == 0 || parts[0].isEmpty()) {
            return "";
        }

        String command = parts[0].toLowerCase();

        if (command.equals("open") && parts.length >= 2) {
            return new OpenCommand(repository, parts[1]).execute();
        }
        if (command.equals("help")) {
            return new HelpCommand().execute();
        }
        if (command.equals("exit")) {
            return new ExitCommand().execute();
        }

        if (command.equals("save") && parts.length >= 3 && parts[1].equals("as")) {
            return new SaveAsCommand(repository, parts[2]).execute();
        }

        if (!repository.isFileOpened()) {
            return Messages.OPEN_FILE_FIRST;
        }

        if (command.equals("close")) {
            return new CloseCommand(repository).execute();
        }
        if (command.equals("save")) {
            return new SaveCommand(repository).execute();
        }
        if (command.equals("print")) {
            return new PrintCommand(repository).execute();
        }

        if (command.equals("create") && parts.length >= 3) {
            String type = parts[1].toLowerCase();
            
            try {
                if (type.equals("rectangle") && parts.length >= 7) {
                    double x = Double.parseDouble(parts[2]);
                    double y = Double.parseDouble(parts[3]);
                    double width = Double.parseDouble(parts[4]);
                    double height = Double.parseDouble(parts[5]);
                    String color = parts[6];
                    
                    Rectangle rect = new Rectangle(x, y, width, height, color);
                    return new CreateCommand(repository, rect).execute();
                    
                } else if (type.equals("circle") && parts.length >= 6) {
                    double cx = Double.parseDouble(parts[2]);
                    double cy = Double.parseDouble(parts[3]);
                    double r = Double.parseDouble(parts[4]);
                    String color = parts[5];
                    
                    Circle circle = new Circle(cx, cy, r, color);
                    return new CreateCommand(repository, circle).execute();
                    
                } else if (type.equals("line") && parts.length >= 7) {
                    double x1 = Double.parseDouble(parts[2]);
                    double y1 = Double.parseDouble(parts[3]);
                    double x2 = Double.parseDouble(parts[4]);
                    double y2 = Double.parseDouble(parts[5]);
                    String color = parts[6];
                    
                    Line line = new Line(x1, y1, x2, y2, color);
                    return new CreateCommand(repository, line).execute();
                }
                return Messages.UNKNOWN_SHAPE_TYPE;
            } catch (Exception e) {
                return "Invalid parameters: " + e.getMessage();
            }
        }

        if (command.equals("erase") && parts.length >= 2) {
            int number = Integer.parseInt(parts[1]);
            return new EraseCommand(repository, number).execute();
        }

        if (command.equals("translate")) {
            double horizontal = 0;
            double vertical = 0;
            int figureNumber = 0;

            for (int i = 1; i < parts.length; i++) {
                if (parts[i].startsWith("horizontal=")) {
                    horizontal = Double.parseDouble(parts[i].split("=")[1]);
                } else if (parts[i].startsWith("vertical=")) {
                    vertical = Double.parseDouble(parts[i].split("=")[1]);
                } else {
                    figureNumber = Integer.parseInt(parts[i]);
                }
            }
            return new TranslateCommand(repository, horizontal, vertical, figureNumber).execute();
        }

        if (command.equals("within") && parts.length >= 3) {
            String regionType = parts[1].toLowerCase();
            Shape region;
            
            try {
                if (regionType.equals("rectangle") && parts.length >= 6) {
                    region = new Rectangle(
                            Double.parseDouble(parts[2]), 
                            Double.parseDouble(parts[3]), 
                            Double.parseDouble(parts[4]), 
                            Double.parseDouble(parts[5]), 
                            "");
                } else if (regionType.equals("circle") && parts.length >= 5) {
                    region = new Circle(
                            Double.parseDouble(parts[2]), 
                            Double.parseDouble(parts[3]), 
                            Double.parseDouble(parts[4]), 
                            "");
                } else {
                    return "Unknown region type. Use 'rectangle' or 'circle'.";
                }
                
                return new WithinCommand(repository, region).execute();
            } catch (Exception e) {
                return "Invalid parameters: " + e.getMessage();
            }
        }

        return Messages.UNKNOWN_COMMAND;
    }
}