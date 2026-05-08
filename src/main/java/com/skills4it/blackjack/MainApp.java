package com.skills4it.blackjack;

import com.skills4it.blackjack.enums.BettingOption;
import com.skills4it.blackjack.enums.PlayerRank;
import com.skills4it.blackjack.service.BlackjackGame;
import com.skills4it.blackjack.service.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MainApp starts the program and manages console input/output.
 *
 * Teaching point:
 * MainApp coordinates the flow.
 * The real card logic is inside Card, Deck, Hand, Player, and BlackjackGame.
 */
public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcome();

        int numberOfPlayers = askForNumberOfPlayers();
        List<String> playerNames = askForPlayerNames(numberOfPlayers);

        BlackjackGame game = new BlackjackGame(playerNames);
        choosePlayerBets(game);
        game.dealStartingCards();

        playTurns(game);
        printResults(game);
    }

    private static void printWelcome() {
        System.out.println("================================");
        System.out.println("        Blackjack Demo");
        System.out.println("================================");
        System.out.println();
    }

    private static int askForNumberOfPlayers() {
        while (true) {
            System.out.print("How many players are playing? ");

            try {
                int numberOfPlayers = Integer.parseInt(scanner.nextLine());

                if (numberOfPlayers >= 2 && numberOfPlayers <= 6) {
                    return numberOfPlayers;
                }

                System.out.println("Please enter a number between 2 and 6.");
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static List<String> askForPlayerNames(int numberOfPlayers) {
        List<String> names = new ArrayList<>();

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.print("Enter name for player " + i + ": ");
            String name = scanner.nextLine();
            names.add(name);
        }

        return names;
    }
    private static void choosePlayerBets(BlackjackGame game) {

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
    private static void playTurns(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            System.out.println();
            System.out.println(player.getName() + "'s turn");
            System.out.println(player);

            while (!player.isBust()) {
                System.out.print("Hit or Stay? (h/s): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("s") || choice.equals("stay")) {
                    break;
                }

                if (choice.equals("h") || choice.equals("hit")) {
                    game.hit(player);
                    System.out.println(player);
                } else {
                    System.out.println("Please type h for Hit or s for Stay.");
                }
            }
        }
    }

    private static void displayPlayerRanks(BlackjackGame game) {
        System.out.println();
        System.out.println("===============================");
        System.out.println("             RANKS             ");
        System.out.println("===============================");

        for (Player player : game.getPlayers()) {

            String rankName = "Unranked";//this prevents crashing at when tryin to display rank
//this assumes player has no rank until the game is done, then assigns one under line 181 and 183
            if (player.getRank() != null) {
                rankName = player.getRank().getDisplayName();
            }

            System.out.println(
                    player.getName() + " - " + rankName
            );
        }
    }
    private static void printResults(BlackjackGame game) {
        System.out.println();
        System.out.println("================================");
        System.out.println("            Results");
        System.out.println("================================");

        for (Player player : game.getPlayers()) {
            System.out.println(player);
        }

        Player winner = game.determineWinner();
        for (Player player : game.getPlayers()) {

            if (player == winner) {
                player.setRank(PlayerRank.GOLD);
            } else {//------- assign ranks at the end of the game also
                player.setRank(PlayerRank.SILVER);
            }
        }
        // ----------------------------------------------------------ranks
        if (winner != null) {
            winner.setRank(PlayerRank.GOLD);
        }

        System.out.println();

        if (winner == null) {
            System.out.println("Everyone busted. There is no winner.");
        } else if (game.hasTieForWinningScore()) {
            System.out.println("There is a tie with " + winner.getScore() + " points.");
        } else {
            System.out.println("Winner: " + winner.getName() + " with " + winner.getScore() + " points.");
        }

        //----------------------------------------------------- Display ranks
        displayPlayerRanks(game);
    }
}