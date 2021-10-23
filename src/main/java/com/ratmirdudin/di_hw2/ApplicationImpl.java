package com.ratmirdudin.di_hw2;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ApplicationImpl implements Application {

    private final Logger logger;

    @Inject
    public ApplicationImpl(@NotNull Logger logger) {
        this.logger = logger;
    }

    @Override
    public void waitForInput(@NotNull String[] args) throws IOException {
        if (args.length == 1) {
            args = args[0].split(" ");
        }

        if (!isCorrect(args)) {
            return;
        }

        settingUpLogger(args);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");
            while (true) {
                logger.info(scanner.nextLine());
            }
        } catch (IllegalStateException | NoSuchElementException ignored) {
        }

    }

    private void settingUpLogger(@NotNull String[] args) throws IOException {
        this.logger.setUseParentHandlers(false);

        boolean consoleLogFlag = false;
        boolean fileLogFlag = false;

        if (args[0].equals("console") || args[0].equals("composite")) {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new MyFormatter());
            this.logger.addHandler(consoleHandler);
            consoleLogFlag = true;
        }

        if (args[0].equals("file") || args[0].equals("composite")) {
            FileHandler fileHandler = new FileHandler("%h/java_dependency_injection.log");
            fileHandler.setFormatter(new MyFormatter(args[1]));
            this.logger.addHandler(fileHandler);
            fileLogFlag = true;
        }

        if (consoleLogFlag && fileLogFlag) {
            System.out.println("Composite logs mod");
        } else if (consoleLogFlag) {
            System.out.println("Console logs mod");
        } else if (fileLogFlag) {
            System.out.println("File logs mod");
        }
    }

    private boolean isCorrect(@NotNull String[] args) {
        if (
                !(
                        (args.length >= 1 && args.length <= 2)
                                && (args[0].equals("console") && args.length == 1
                                || (args[0].equals("file") || args[0].equals("composite")) && args.length == 2)
                )
        ) {
            System.out.println("***************WARNING***************");
            System.out.println("Please, start program with argument.");
            System.out.println("To start with console logs make argument = console.");
            System.out.println("To start with file logs make arguments = file *tag*, \n" +
                    "where *tag* is tag for log's file.");
            System.out.println("To start with console and file logs make argument = composite *tag*, \n" +
                    "where *tag* is tag for log's file.");
            System.out.println("You can use and set up a special task \"startMainClass\" of group \"launch\".");
            System.out.println("*************************************");
            return false;
        }
        return true;
    }
}
