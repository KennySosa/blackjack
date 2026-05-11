package com.skills4it.blackjack.ui;

import com.skills4it.blackjack.enums.BettingOption;
import com.skills4it.blackjack.enums.PlayerAction;
import com.skills4it.blackjack.enums.PlayerRank;
import com.skills4it.blackjack.Game.BlackjackGame;
import com.skills4it.blackjack.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackConsoleUI {

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 6;

    private final Scanner scanner = new Scanner(System.in);

    public void start() {

        printWelcome();

        int numberOfPlayers = askForNumberOfPlayers();

        List<String> playerNames = askForPlayerNames(numberOfPlayers);

        BlackjackGame game = new BlackjackGame(playerNames);

        choosePlayerBets(game);

        game.dealStartingCards();

        playTurns(game);

        printResults(game);
    }

    private void printWelcome() {
        System.out.println("================================");
        System.out.println("        Blackjack Demo");
        System.out.println("================================");
        System.out.println();
    }

    private int askForNumberOfPlayers() {
        while (true) {
            System.out.println("How many players are playing?");
            System.out.print("Enter a number between " + MIN_PLAYERS + " and " + MAX_PLAYERS + ": ");

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a number.");
                continue;
            }

            try {
                int numberOfPlayers = Integer.parseInt(input);

                if (numberOfPlayers >= MIN_PLAYERS && numberOfPlayers <= MAX_PLAYERS) {
                    return numberOfPlayers;
                }

                System.out.println("Please enter a number between " + MIN_PLAYERS + " and " + MAX_PLAYERS + ".");
            } catch (NumberFormatException exception) {
                System.out.println("'" + input + "' is not a valid whole number.");
            }
        }
    }

    private List<String> askForPlayerNames(int numberOfPlayers) {
        List<String> names = new ArrayList<>();

        for (int i = 1; i <= numberOfPlayers; i++) {
            String name = askForSinglePlayerName(i, names);
            names.add(name);
        }

        return names;
    }

    private String askForSinglePlayerName(int playerNumber, List<String> existingNames) {
        while (true) {
            System.out.print("Enter name for player " + playerNumber + ": ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                continue;
            }

            if (nameAlreadyExists(name, existingNames)) {
                System.out.println("This name is already used. Please choose another name.");
                continue;
            }

            return name;
        }
    }

    private boolean nameAlreadyExists(String name, List<String> existingNames) {
        for (String existingName : existingNames) {
            if (existingName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private PlayerAction askForPlayerAction() {
        while (true) {
            System.out.print("Hit or Stay? (h/s): ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "h":
                case "hit":
                    return PlayerAction.HIT;
                case "s":
                case "stay":
                    return PlayerAction.STAY;
                default:
                    System.out.println("Please type h for Hit or s for Stay.");
            }
        }
    }

    private void choosePlayerBets(BlackjackGame game) {

        for (Player player : game.getPlayers()) {

            System.out.println();
            System.out.println(player.getName() + ", choose your betting option:");
            System.out.println("1. LOW ($100)");
            System.out.println("2. MEDIUM ($250)");
            System.out.println("3. HIGH ($500)");
            System.out.println("4. VIP ($1000)");

            while (true) {

                System.out.print("Enter choice (1-4): ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        player.setBettingOption(BettingOption.LOW);
                        break;
                    case "2":
                        player.setBettingOption(BettingOption.MEDIUM);
                        break;
                    case "3":
                        player.setBettingOption(BettingOption.HIGH);
                        break;
                    case "4":
                        player.setBettingOption(BettingOption.VIP);
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-4.");
                        continue;
                }

                break;
            }

            System.out.println(
                    player.getName()
                            + " selected "
                            + player.getBettingOption().getDisplayName()
                            + " ($"
                            + player.getBettingOption().getAmount()
                            + ")"
            );
        }
    }

    private void playTurns(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            System.out.println();
            System.out.println(player.getName() + "'s turn");
            System.out.println(player);

            while (!player.isBust()) {
                PlayerAction action = askForPlayerAction();

                if (action == PlayerAction.STAY) {
                    break;
                }

                if (action == PlayerAction.HIT) {
                    game.hit(player);
                    System.out.println(player);
                }
            }
        }
    }

    private void displayPlayerRanks(BlackjackGame game) {
        System.out.println();
        System.out.println("===============================");
        System.out.println("             RANKS             ");
        System.out.println("===============================");

        for (Player player : game.getPlayers()) {
            System.out.println(player);
        }
    }

    private void printResults(BlackjackGame game) {
        System.out.println();
        System.out.println("================================");
        System.out.println("            Results");
        System.out.println("================================");

        for (Player player : game.getPlayers()) {
            System.out.println(player);
        }

        Player winner = game.determineWinner();

        if (winner != null) {
            for (Player player : game.getPlayers()) {
                if (!player.isBust() && player.getScore() == winner.getScore()) {
                    player.setRank(PlayerRank.GOLD);
                } else {
                    player.setRank(PlayerRank.SILVER);
                }
            }
        }

        System.out.println();

        if (winner == null) {
            System.out.println("Everyone busted. There is no winner.");
        } else if (game.hasTieForWinningScore()) {
            System.out.println("There is a tie with " + winner.getScore() + " points.");
        } else {
            System.out.println("Winner: " + winner.getName() + " with " + winner.getScore() + " points.");
        }

        displayPlayerRanks(game);
    }
}