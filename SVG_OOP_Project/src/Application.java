import Core.Controller;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            String result = controller.execute(input);

            if (result.equals("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            if (result.startsWith("fatal:")) {
                System.out.println(result.substring(6));
                break;
            }

            System.out.println(result);
        }

        scanner.close();
    }
}