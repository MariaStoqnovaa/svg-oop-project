package Commands.implementation.edit;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import Models.Shape;
import java.util.List;

/**
 * Command that translates one shape or all stored shapes by the given offsets.
 */
public class TranslateCommand extends Command {

    private Messages messages = new Messages();
    private ShapeRepository repository;
    private double horizontal;
    private double vertical;
    private int figureNumber;
    /**
     * Creates a translate command.
     *
     * @param repository active shape repository
     * @param horizontal horizontal movement amount
     * @param vertical vertical movement amount
     * @param figureNumber one-based shape number, or {@code 0} for all shapes
     */
    public TranslateCommand(ShapeRepository repository, double horizontal, double vertical, int figureNumber) {
        this.repository = repository;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.figureNumber = figureNumber;
    }
    /**
     * Translates the selected shape or all shapes.
     *
     * @return success or error message
     */
    @Override
    public String execute() {
        List<Shape> shapes = repository.getAll();

        if (figureNumber > 0) {
            int index = figureNumber - 1;
            if (index < 0 || index >= shapes.size())
            {
                return messages.NO_FIGURE_WITH_NUMBER + figureNumber + "!";
            }
            shapes.get(index).translate(horizontal, vertical);
            return messages.TRANSLATED_FIGURE + figureNumber;
        } else {
            for (Shape shape : shapes) {
                shape.translate(horizontal, vertical);
            }
            return messages.TRANSLATED_ALL_FIGURES;
        }
    }
}
