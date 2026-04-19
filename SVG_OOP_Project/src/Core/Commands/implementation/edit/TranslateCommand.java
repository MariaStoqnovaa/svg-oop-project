package Core.Commands.implementation.edit;

import Core.Commands.base.Command;
import Core.ShapeRepository;
import Core.Messages;
import Models.Shape;
import java.util.List;


public class TranslateCommand extends Command {

    private ShapeRepository repository;
    private double horizontal;
    private double vertical;
    private int figureNumber;

    public TranslateCommand(ShapeRepository repository, double horizontal, double vertical, int figureNumber) {
        this.repository = repository;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.figureNumber = figureNumber;
    }

    @Override
    public String execute() {
        List<Shape> shapes = repository.getAll();

        if (figureNumber > 0) {
            int index = figureNumber - 1;
            if (index < 0 || index >= shapes.size()) {
                return Messages.NO_FIGURE_WITH_NUMBER + figureNumber + "!";
            }
            shapes.get(index).translate(horizontal, vertical);
            return Messages.TRANSLATED_FIGURE + figureNumber;
        } else {
            for (Shape shape : shapes) {
                shape.translate(horizontal, vertical);
            }
            return Messages.TRANSLATED_ALL_FIGURES;
        }
    }
}
