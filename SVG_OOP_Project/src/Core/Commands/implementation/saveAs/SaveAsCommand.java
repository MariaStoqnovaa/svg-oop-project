package Core.Commands.implementation.saveAs;

import Core.Commands.base.Command;
import Core.ShapeRepository;
import Core.Messages;
import java.io.File;

public class SaveAsCommand extends Command {

    private ShapeRepository repository;
    private String newFilePath;

    public SaveAsCommand(ShapeRepository repository, String newFilePath) {
        this.repository = repository;
        this.newFilePath = newFilePath;
    }

    @Override
    public String execute() {
        if (!repository.isFileOpened()) {
            return Messages.NO_FILE_OPENED_MESSAGE;
        }

        String fileName = new File(newFilePath).getName();

        try {
            String svgContent = repository.getSvgService().serialize(repository);
            repository.getFileService().writeFile(newFilePath, svgContent);
            repository.setCurrentFilePath(newFilePath);
            return Messages.FILE_SAVED_SUCCESSFULLY + fileName;
        } catch (Exception e) {
            return Messages.FILE_SAVE_ERROR + e.getMessage();
        }
    }
}
