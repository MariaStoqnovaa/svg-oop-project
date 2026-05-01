package Commands.implementation.edit;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import Models.Shape;


public class EraseCommand extends Command {

    private Messages messages = new Messages();
    private ShapeRepository repository;
    private int figureNumber;

    public EraseCommand(ShapeRepository repository, int figureNumber) {
        this.repository = repository;
        this.figureNumber = figureNumber;
    }

    @Override
    public String execute() {
        int index = figureNumber - 1;

        if (index < 0 || index >= repository.getAll().size()) {
            return messages.NO_FIGURE_WITH_NUMBER + figureNumber + "!";
        }

        Shape removed = repository.remove(index);
        String shapeName = removed.getClass().getSimpleName();
        return messages.SHAPE_ERASED_SUCCESSFULLY + shapeName + " (" + figureNumber + ")";
    }
}
