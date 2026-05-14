package Commands.implementation.print;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import Models.Shape;
import java.util.List;
/**
 * Command that prints all shapes currently stored in the repository.
 */
public class PrintCommand extends Command {

    private Messages messages = new Messages();
    private ShapeRepository repository;
    /**
     * Creates a print command.
     *
     * @param repository active shape repository
     */
    public PrintCommand(ShapeRepository repository) {
        this.repository = repository;
    }
    /**
     * Builds a numbered list of all stored shapes.
     *
     * @return shape list or a message that no shapes are loaded
     */
    @Override
    public String execute() {
        List<Shape> shapes = repository.getAll();

        if (shapes.isEmpty())
        {
            return messages.NO_SHAPES_LOADED;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shapes.size(); i++)
        {
            result.append(i + 1).append(". ").append(shapes.get(i).toString()).append("\n");
        }

        result.setLength(result.length() - 1);
        return result.toString();
    }
}
