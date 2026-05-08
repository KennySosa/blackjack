package com.skills4it.blackjack.enums;

/**
 * Represents the rank/value printed on a card.
 * Each rank also has a Blackjack point value.
 */
public enum Rank {
	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),

	// Face cards are worth 10 points in Blackjack.
	JACK("J", 10),
	QUEEN("Q", 10),
	KING("K", 10),

	// Ace is worth 11 by default.
	ACE("A", 11);

	// Text shown when displaying the card, for example "A" or "K".
	private final String displayName;

	// Default point value used for Blackjack scoring.
	private final int pointValue;

	/**
	 * Creates a rank with a display name and point value.
	 */
	Rank(String displayName, int pointValue) {
		this.displayName = displayName;
		this.pointValue = pointValue;
	}

	/**
	 * Returns the text shown for this rank.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Returns the Blackjack point value for this rank.
	 */
	public int getPointValue() {
		return pointValue;
	}
}