package Core.Commands.implementation.save;

import Core.Commands.base.Command;
import Core.ShapeRepository;
import Core.Messages;

public class SaveCommand extends Command {

    private ShapeRepository repository;

    public SaveCommand(ShapeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        if (!repository.isFileOpened()) {
            return Messages.NO_FILE_OPENED_MESSAGE;
        }

        try {
            String svgContent = repository.getSvgService().serialize(repository);
            repository.getFileService().writeFile(repository.getCurrentFilePath(), svgContent);
            return Messages.FILE_SAVED_SUCCESSFULLY + repository.getCurrentFileName();
        } catch (Exception e) {
            return Messages.FILE_SAVE_ERROR + e.getMessage();
        }
    }
}