package com.skills4it.blackjack.enums;

/**
 * Represents the four suits in a standard deck of cards.
 */
public enum Suit {
    HEARTS("Hearts"),
    SPADES("Spades"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs");

    private final String displayName;

    Suit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
