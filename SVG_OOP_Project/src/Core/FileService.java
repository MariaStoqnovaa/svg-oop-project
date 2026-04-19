package Core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileService {

    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return "";
        }

        StringBuilder content = new StringBuilder();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine()).append("\n");
        }

        scanner.close();
        return content.toString();
    }

    public void writeFile(String filePath, String content) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(content);
        writer.close();
    }
}
