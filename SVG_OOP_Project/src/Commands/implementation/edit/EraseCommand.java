package Commands.implementation.edit;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import Models.Shape;

/**
 * Command that removes a shape by its user-visible number.
 */
public class EraseCommand extends Command {

    private Messages messages = new Messages();
    private ShapeRepository repository;
    private int figureNumber;

    /**
     * Creates an erase command.
     *
     * @param repository active shape repository
     * @param figureNumber one-based number of the shape to remove
     */
    public EraseCommand(ShapeRepository repository, int figureNumber) {
        this.repository = repository;
        this.figureNumber = figureNumber;
    }
    /**
     * Removes the requested shape if the number is valid.
     *
     * @return success or error message
     */
    @Override
    public String execute() {
        int index = figureNumber - 1;

        if (index < 0 || index >= repository.getAll().size()) {
            return messages.NO_FIGURE_WITH_NUMBER + figureNumber + "!";
        }

        Shape removed = repository.remove(index);
        String shapeName = removed.getClass().getSimpleName().toLowerCase();
        return messages.SHAPE_ERASED_SUCCESSFULLY + shapeName + " (" + figureNumber + ")";
    }
}
