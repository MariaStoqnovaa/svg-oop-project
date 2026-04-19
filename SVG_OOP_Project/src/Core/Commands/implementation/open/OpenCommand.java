package Core.Commands.implementation.open;

import Core.Commands.base.Command;
import Core.ShapeRepository;
import Core.FileService;
import Core.SvgService;
import Core.IShapeRepository;
import Core.Messages;
import java.io.File;

public class OpenCommand extends Command {

    private ShapeRepository repository;
    private String filePath;

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

            return Messages.FILE_OPENED_SUCCESSFULLY + fileName;
        } catch (Exception e) {
            return "fatal:" + Messages.FILE_LOAD_ERROR + e.getMessage();
        }
    }
}