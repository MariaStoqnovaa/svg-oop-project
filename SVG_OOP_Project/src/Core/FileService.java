package Core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Provides basic text file input and output operations for the project.
 *
 * <p>This class is used to read SVG or text content from disk and to write the
 * generated SVG content back to a file.</p>
 */

public class FileService {

    /**
     * Reads the full content of a text file.
     *
     * @param filePath path to the file to read
     * @return the file content, or an empty string if the file does not exist
     * @throws IOException if the file exists but cannot be read
     */

    /**
     * Reads the entire content of the given text file.
     * Returns an empty string if the file does not exist, so callers can
     * treat a missing file as an empty document.
     *
     * @param filePath path to the file to read
     * @return the file's content, or an empty string if the file is missing
     * @throws IOException if the file exists but cannot be read
     */
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
    
    /**
     * Writes text content to a file, replacing any existing content.
     *
     * @param filePath path to the destination file
     * @param content text to write
     * @throws IOException if the file cannot be written
     */

    /**
     * Writes the given content to the file at {@code filePath},
     * overwriting any existing content.
     *
     * @param filePath path to the file to write
     * @param content  text to write to the file
     * @throws IOException if the file cannot be written
     */
    public void writeFile(String filePath, String content) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(content);
        writer.close();
    }
}
