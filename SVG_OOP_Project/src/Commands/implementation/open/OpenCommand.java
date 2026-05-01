package Commands.implementation.open;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import java.io.File;

public class OpenCommand extends Command {

    private ShapeRepository repository;
    private String filePath;
    private Messages messages = new Messages();

    public OpenCommand(ShapeRepository repository, String filePath) {
        this.repository = repository;
        this.filePath = filePath;
    }

    @Override
    public String execute() {
        String fileName = new File(filePath).getName();

        try {
            String content = repository.getFileService().readFile(filePath);
            repository.setCurrentFilePath(filePath);

            if (!content.isEmpty()) {
                repository.getSvgService().parse(content, repository);
            }

            return messages.FILE_OPENED_SUCCESSFULLY + fileName;
        } catch (Exception e) {
            return "fatal:" + messages.FILE_LOAD_ERROR + e.getMessage();
        }
    }
}