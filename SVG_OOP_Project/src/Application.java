import Core.Controller;
import java.util.Scanner;

/**
 * Entry point of the SVG console application.
 *
 * <p>This class starts the read-eval-print loop, reads commands from standard
 * input, delegates command processing to {@link Core.Controller}, and prints
 * the returned result to the console.</p>
 */

public class Application {

    /**
     * Starts the console application.
     *
     * <p>The method continuously reads user input, passes each command line to
     * the controller, prints the command result, and stops when the controller
     * returns the special exit marker or a fatal error.</p>
     *
     * @param args command-line arguments, currently unused
     */
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