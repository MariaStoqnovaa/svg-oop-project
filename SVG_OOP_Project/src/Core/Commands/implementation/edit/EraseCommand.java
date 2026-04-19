package Core.Commands.implementation.edit;

import Core.Commands.base.Command;
import Core.ShapeRepository;
import Core.Messages;
import Models.Shape;


public class EraseCommand extends Command {

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
            return Messages.NO_FIGURE_WITH_NUMBER + figureNumber + "!";
        }

        Shape removed = repository.remove(index);
        String shapeName = removed.toString().split(" ")[0];
        return Messages.SHAPE_ERASED_SUCCESSFULLY + shapeName + " (" + figureNumber + ")";
    }
}
