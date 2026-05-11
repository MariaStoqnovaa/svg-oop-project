package Core;

import Models.Circle;
import Models.Line;
import Models.Rectangle;
import Models.Shape;
import Commands.implementation.open.OpenCommand;
import Commands.implementation.close.CloseCommand;
import Commands.implementation.save.SaveCommand;
import Commands.implementation.saveAs.SaveAsCommand;
import Commands.implementation.help.HelpCommand;
import Commands.implementation.exit.ExitCommand;
import Commands.implementation.print.PrintCommand;
import Commands.implementation.edit.CreateCommand;
import Commands.implementation.edit.EraseCommand;
import Commands.implementation.edit.TranslateCommand;
import Commands.implementation.edit.WithinCommand;

/**
 * Parses raw user input from the console and dispatches each line to
 * the appropriate command in the {@code Commands.implementation}
 * package. Holds the single {@link ShapeRepository} that all commands
 * operate on for the duration of the session.
 *
 * Recognised commands: {@code open}, {@code close}, {@code save},
 * {@code save as}, {@code help}, {@code exit}, {@code print},
 * {@code create}, {@code erase}, {@code translate}, {@code within}.
 */
public class Controller {

    private ShapeRepository repository = new ShapeRepository();

    /**
     * Parses one line of user input, executes the matching command and
     * returns its textual result. Malformed input (missing arguments,
     * non-numeric coordinates, unknown command) is reported as a short
     * error message rather than thrown as an exception, so the calling
     * REPL loop can simply print whatever this method returns.
     *
     * @param input a single command line entered by the user
     * @return the command's output, or an error / help message
     */

    public String execute(String input) {
        String[] parts = input.trim().split("\\s+");

        if (parts.length == 0 || parts[0].isEmpty())
        {
            return null;
        }

        String command = parts[0].toLowerCase();

        if (command.equals("open"))
        {
            if (parts.length < 2)
            {
                return "Write full filename.";
            }
            else
            {
                return new OpenCommand(repository, parts[1]).execute();
            }

        }

        if (command.equals("close"))
        {
            return new CloseCommand(repository).execute();
        }

        if (command.equals("save")) {
            if (parts.length >= 3 && parts[1].equals("as"))
            {
                return new SaveAsCommand(repository, parts[2]).execute();
            }
            else
            {
                return new SaveCommand(repository).execute();
            }

        }

        if (command.equals("help")) {
            return new HelpCommand().execute();
        }

        if (command.equals("exit")) {
            return new ExitCommand().execute();
        }

        if (command.equals("print")) {
            return new PrintCommand(repository).execute();
        }

        if (command.equals("create")) {
            if (parts.length < 2)
            {
                return "Please give a shape type.";
            }
            else {
                String type = parts[1].toLowerCase();

                try {
                    Shape shape = null;

                    if (type.equals("rectangle") && parts.length >= 7)
                    {
                        double x = Double.parseDouble(parts[2]);
                        double y = Double.parseDouble(parts[3]);
                        double width = Double.parseDouble(parts[4]);
                        double height = Double.parseDouble(parts[5]);
                        String color = parts[6];
                        shape = new Rectangle(x, y, width, height, color);

                    }
                    else if (type.equals("circle") && parts.length >= 6)
                    {
                        double cx = Double.parseDouble(parts[2]);
                        double cy = Double.parseDouble(parts[3]);
                        double r = Double.parseDouble(parts[4]);
                        String color = parts[5];
                        shape = new Circle(cx, cy, r, color);

                    }
                    else if (type.equals("line") && parts.length >= 7)
                    {
                        double x1 = Double.parseDouble(parts[2]);
                        double y1 = Double.parseDouble(parts[3]);
                        double x2 = Double.parseDouble(parts[4]);
                        double y2 = Double.parseDouble(parts[5]);
                        String color = parts[6];
                        shape = new Line(x1, y1, x2, y2, color);
                    }

                    if (shape != null)
                    {
                        return new CreateCommand(repository, shape).execute();
                    }
                    else
                    {
                        return "Unknown shape or insufficient parameters. Use: rectangle, circle, or line.";
                    }
                }
                catch (NumberFormatException e)
                {
                    return "Invalid number in parameters.";
                }
            }
        }

        if (command.equals("erase")) {
            if (parts.length < 2) {
                return "Give a shape number.";
            }
            try
            {
                int index = Integer.parseInt(parts[1]);
                return new EraseCommand(repository, index).execute();
            }
            catch (NumberFormatException e) {
                return "Shape number isn't correct.";
            }
        }

        if (command.equals("translate"))
        {
            double horizontal = 0;
            double vertical = 0;
            int figureNumber = 0;

            try {
                for (int i = 1; i < parts.length; i++) {
                    if (parts[i].startsWith("horizontal="))
                    {
                        horizontal = Double.parseDouble(parts[i].split("=")[1]);
                    }
                    else if (parts[i].startsWith("vertical="))
                    {
                        vertical = Double.parseDouble(parts[i].split("=")[1]);
                    }
                    else
                    {
                        figureNumber = Integer.parseInt(parts[i]);
                    }
                }
                return new TranslateCommand(repository, horizontal, vertical, figureNumber).execute();
            } catch (Exception e) {
                return "Invalid parameters for translate.";
            }
        }

        if (command.equals("within")) {
            if (parts.length < 3) {
                return "Give a region type and parameters.";
            }
            String regionType = parts[1].toLowerCase();

            try {
                Shape region;
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
                }
                else {
                    return "Unknown region type or insufficient";
                }

                return new WithinCommand(repository, region).execute();

            }
            catch (NumberFormatException e) {
                return "Paramether number isn't correct";
            }
        }
        return "Unknown command. Type 'help' to see all the commands.";
    }
}