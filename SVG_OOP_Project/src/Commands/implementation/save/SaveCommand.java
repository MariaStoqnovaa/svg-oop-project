package Commands.implementation.save;

import Commands.base.Command;
import Core.ShapeRepository;
import Models.Shape;
import constants.Messages;
import java.util.List;

public class SaveCommand extends Command {

    private ShapeRepository repository;
    private Messages messages = new Messages();

    public SaveCommand(ShapeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        if (!repository.isFileOpened()) {
            return messages.NO_FILE_OPENED_MESSAGE;
        }

        try {
            String svgContent = repository.getSvgService().serialize(repository);
            repository.getFileService().writeFile(repository.getCurrentFilePath(), svgContent);
            return messages.FILE_SAVED_SUCCESSFULLY + repository.getCurrentFileName();

//            StringBuilder svgContent = new StringBuilder();
//            svgContent.append("<?xml version=\"1.0\" standalone=\"no\"?>\n");
//            svgContent.append("<svg>\n");
//            for (Shape shape : repository.getAll()) {
//                svgContent.append("  ").append(shape.toSVG()).append("\n");
//            }
//            svgContent.append("</svg>");
//            repository.getFileService().writeFile(repository.getCurrentFilePath(), svgContent.toString());
//            return messages.FILE_SAVED_SUCCESSFULLY + repository.getCurrentFileName();
//
        } catch (Exception e) {
            return messages.FILE_SAVE_ERROR + e.getMessage();
        }
    }
}