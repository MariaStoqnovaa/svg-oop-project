package Core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ShapeFiles
{
    public String readFile(String filePath) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(filePath);

       if(!file.exists())
       {
           return "File doesn't exist.";
       }
       else{
           Scanner scanner = new Scanner(file);

           while (scanner.hasNextLine())
           {
               stringBuilder.append(scanner.nextLine()).append("\n");
           }
           scanner.close();
           return stringBuilder.toString();
       }
    }

    public void writeFile(String filePath, String text) throws  IOException
    {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(text);
        fileWriter.close();
    }
}
