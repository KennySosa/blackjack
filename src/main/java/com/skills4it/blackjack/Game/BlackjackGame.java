package com.skills4it.blackjack.Game;

import com.skills4it.blackjack.models.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BlackjackGame coordinates the game flow.
 *
 * This class represents the "game controller" of the application.
 * It connects the Deck and Player objects together, but it does not do
 * all the work itself.
 *
 * It has:
 * - one Deck
 * - many Players
 *
 * Important OOP idea:
 * This class does NOT calculate card values directly.
 * It asks the Player and Hand objects for that information.
 *
 * This keeps the classes loosely coupled and gives each class
 * a clear responsibility.
 */
public class BlackjackGame {

	// The game has one deck.
	// final means this variable must be assigned once and cannot point to another Deck later.
	private final Deck deck;

	// The game has many players.
	// We use an ArrayList because the number of players can vary.
	private final ArrayList<Player> players;

	/**
	 * Creates a new BlackjackGame.
	 *
	 * The constructor receives a list of player names.
	 * For each name, it creates a new Player object.
	 *
	 * It also creates and shuffles the deck so the game is ready to start.
	 *
	 * @param playerNames the names of the players who will join the game
	 */
	public BlackjackGame(List<String> playerNames) {

		// Create a new deck with all cards.
		deck = new Deck();

		// Shuffle the deck before dealing cards.
		deck.shuffle();

		// Create an empty list that will store all Player objects.
		players = new ArrayList<>();

		// Convert every player name into a Player object.
		for (String playerName : playerNames) {
			players.add(new Player(playerName));
		}
	}

	/**
	 * Deals the starting cards for a Blackjack game.
	 *
	 * In Blackjack, each player starts with 2 cards.
	 *
	 * The outer loop represents the round:
	 * - round 0: every player receives their first card
	 * - round 1: every player receives their second card
	 *
	 * The inner loop goes through each player and gives them one card.
	 */
	public void dealStartingCards() {

		// Deal 2 rounds of cards.
		for (int round = 0; round < 2; round++) {

			// Give one card to each player during each round.
			for (Player player : players) {

				// Ask the deck to deal one card.
				// Then give that card to the current player.
				player.receiveCard(deck.deal());
			}
		}
	}

	/**
	 * Gives one extra card to a player.
	 *
	 * In Blackjack this is called a "hit".
	 *
	 * Notice:
	 * The game does not decide what happens inside the player's hand.
	 * It only asks the deck for a card and passes it to the player.
	 *
	 * @param player the player who wants another card
	 */
	public void hit(Player player) {

		// Deal one card from the deck and give it to the selected player.
		player.receiveCard(deck.deal());
	}

	/**
	 * Determines which player has won the game.
	 *
	 * A player wins if:
	 * - they are not bust
	 * - their score is higher than the current best score
	 *
	 * If all players are bust, this method returns null.
	 *
	 * @return the winning Player, or null if there is no winner
	 */
	public Player determineWinner() {

		// At the start, we do not have a winner yet.
		Player winner = null;

		// The best score starts at 0.
		// Any valid Blackjack score above 0 can beat this.
		int bestScore = 0;

		// Check every player in the game.
		for (Player player : players) {

			// Ask the player for their score.
			// The game does not calculate the score itself.
			int score = player.getScore();

			// A player can only win if they are not bust.
			// The score must also be better than the best score so far.
			if (!player.isBust() && score > bestScore) {
				bestScore = score;
				winner = player;
			}
		}

		// Return the player with the best valid score.
		// This can be null if every player went bust.
		return winner;
	}

	/**
	 * Checks whether multiple players share the winning score.
	 *
	 * Example:
	 * - Player 1 has 20
	 * - Player 2 has 20
	 * - Player 3 has 18
	 *
	 * In this case, there is a tie for the winning score.
	 *
	 * @return true if more than one player has the winning score, otherwise false
	 */
	public boolean hasTieForWinningScore() {

		// First, determine the winner.
		Player winner = determineWinner();

		// If there is no winner, there cannot be a tie for the winning score.
		// This can happen if all players are bust.
		if (winner == null) {
			return false;
		}

		// Store the winning score so we can compare other players against it.
		int winningScore = winner.getScore();

		// Count how many players have the same winning score.
		int playersWithWinningScore = 0;

		// Check every player.
		for (Player player : players) {

			// Only count players who are not bust and have the same score as the winner.
			if (!player.isBust() && player.getScore() == winningScore) {
				playersWithWinningScore++;
			}
		}

		// If more than one player has the winning score, it is a tie.
		return playersWithWinningScore > 1;
	}

	/**
	 * Returns the deck used in this game.
	 *
	 * This can be useful for testing.
	 * For example, a unit test could check how many cards are left after dealing.
	 *
	 * @return the deck used by the game
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * Returns the list of players.
	 *
	 * We return an unmodifiable list to protect the internal players list.
	 *
	 * This means outside code can read the players,
	 * but it cannot directly add or remove players from the game.
	 *
	 * This supports encapsulation.
	 *
	 * @return a read-only view of the players
	 */
	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}