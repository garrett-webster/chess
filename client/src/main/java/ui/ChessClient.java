package ui;

import static ui.EscapeSequences.*;
import server.ServerFacade;

import java.util.Scanner;

public class ChessClient {
    private final ServerFacade server;
    private State state = State.SIGNEDOUT;

    public ChessClient(String url) {
        server = new ServerFacade(url);
    }

    public void run() {
        System.out.println("Welcome to the chess client. Type \"help\" to see available actions.\n");

        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();

            try {
                switch (line) {
                    case "quit" -> result = "quit";
                    case "help" -> result = help();
                    default -> {
                        if (state == State.SIGNEDOUT) {
                            result = signedOutEval(line);
                        } else if (state == State.SIGNEDIN) {
                            result = signedInEval(line);
                        } else if (state == State.INGAME) {
                            result = inGameEval(line);
                        } else {
                            result = "Unknown command. Type \"help\" to see valid commands.";
                        }
                    }
                };
                System.out.print(SET_TEXT_COLOR_BLUE + result);
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(msg);
            }
        }
    }

    private String signedOutEval(String line) {
        return switch (line) {
          case "help" -> help();
          default -> throw new IllegalStateException("Unexpected value: " + line + "Type help for valid commands.");
        };
    }

    private String signedInEval(String line) {
        return switch (line) {
            case "help" -> help();
            default -> throw new IllegalStateException("Unexpected value: " + line + "Type help for valid commands.");
        };
    }
    private String inGameEval(String line) {
        return switch (line) {
            case "help" -> help();
            default -> throw new IllegalStateException("Unexpected value: " + line + "Type help for valid commands.");
        };
    }

    private void printPrompt() {
        System.out.print("\n" + RESET_TEXT_COLOR + ">>> " + SET_TEXT_COLOR_GREEN);
    }

    private String help() {
        return "Type login to login, blah blah blah";
    }

    private String eval(String line) {
        return switch (line) {
            case "help" -> help();
            case "quit" -> "quit";
            default -> "Could not recognize command";
        };
    }
}
