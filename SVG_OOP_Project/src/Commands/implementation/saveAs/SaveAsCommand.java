package Commands.implementation.saveAs;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import java.io.File;

public class SaveAsCommand extends Command {

    private ShapeRepository repository;
    private String newFilePath;
    private Messages messages = new Messages();

    public SaveAsCommand(ShapeRepository repository, String newFilePath) {
        this.repository = repository;
        this.newFilePath = newFilePath;
    }

    @Override
    public String execute() {
        if (!repository.isFileOpened()) {
            return messages.NO_FILE_OPENED_MESSAGE;
        }

        String fileName = new File(newFilePath).getName();

        try {
            String svgContent = repository.getSvgService().serialize(repository);
            repository.getFileService().writeFile(newFilePath, svgContent);
            repository.setCurrentFilePath(newFilePath);
            return messages.FILE_SAVED_SUCCESSFULLY + fileName;
        } catch (Exception e) {
            return messages.FILE_SAVE_ERROR + e.getMessage();
        }
    }
}
