package Commands.implementation.help;

import Commands.base.Command;

/**
 * Command that returns help information about the supported console commands.
 */
public class HelpCommand extends Command {
    /**
     * Returns a formatted list of supported commands.
     *
     * @return help text
     */
    @Override
    public String execute() {
        return "The following commands are supported:\n"
             + "open <file> - opens <file>\n"
             + "close - closes currently opened file\n"
             + "save - saves the currently open file\n"
             + "save as <file> - saves the currently open file in <file>\n"
             + "print - prints all figures\n"
             + "create - creates a new figure\n"
             + "erase <n> - erases figure number <n>\n"
             + "translate - translates all or one figure\n"
             + "within <region> - prints all figures within <region>\n"
             + "help - prints this information\n"
             + "exit - exits the program";
    }
}
