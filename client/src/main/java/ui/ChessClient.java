package ui;

import static ui.EscapeSequences.*;

import exception.ResponseException;
import requestobjects.LoginRequest;
import requestobjects.RegisterRequest;
import server.ServerFacade;

import java.util.Scanner;

public class ChessClient {
    private final ServerFacade server;
    private State state = State.SIGNEDOUT;
    Scanner scanner = new Scanner(System.in);

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
                if (line.equals("help")) {
                    result = help();
                } else {
                    if (state == State.SIGNEDOUT) {
                        result = signedOutEval(line);
                    } else if (state == State.SIGNEDIN) {
                        result = signedInEval(line);
                    } else if (state == State.INGAME) {
                        result = inGameEval(line);
                    } else {
                        result = "Unknown command. Type \"help\" to see valid commands.";
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
          case "login" -> login();
          case "quit" -> "quit";
          case "register" -> register();
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

    private String register() {
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();

        try {
            server.createUser(new RegisterRequest(username, password, email));
            state = State.SIGNEDIN;
            return "Successfully registered.";
        } catch (ResponseException e) {
            return "Could not complete registration.";
        }
    }

    private String login() {
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        try {
            server.loginUser(new LoginRequest(username, password));
            state = State.SIGNEDIN;
            return "Successfully signed in.";
        } catch (ResponseException e) {
            return "Failed to sign in with the given credentials.";
        }
    }

    private String help() {
        if (state == State.SIGNEDOUT) {
            return "You are currently signed out. Type login to login, blah blah blah";
        } else if (state == State.SIGNEDIN) {
            return "You are currently signed in. Placeholder text";
        } else {
            return "You are currently in a game. Placeholder text";
        }
    }

    private String eval(String line) {
        return switch (line) {
            case "help" -> help();
            case "quit" -> "quit";
            default -> "Could not recognize command";
        };
    }
}
