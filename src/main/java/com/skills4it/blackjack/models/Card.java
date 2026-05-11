package com.skills4it.blackjack.models;

import com.skills4it.blackjack.enums.Rank;
import com.skills4it.blackjack.enums.Suit;

/**
 * A Card is the primary object in the card game.
 *
 * It knows:
 * - its suit
 * - its rank/value
 * - whether it is face up
 *
 * This class does NOT know about Deck, Hand, Player, or Game.
 * That keeps the class loosely coupled.
 */
public class Card {
    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = false;
    }

    /**
     * Returns the suit only when the card is face up.
     */
    public String getSuit() {
        if (faceUp) {
            return suit.getDisplayName();
        }

        return "#";
    }

    /**
     * Returns the card rank only when the card is face up.
     */
    public String getValue() {
        if (faceUp) {
            return rank.getDisplayName();
        }

        return "#";
    }

    /**
     * Returns the point value only when the card is face up.
     *
     * Number cards = numeric value
     * Face cards = 10
     * Ace = 11 by default
     */
    public int getPointValue() {
        if (faceUp) {
            return rank.getPointValue();
        }

        return 0;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public void flip() {
        faceUp = !faceUp;
    }

    public void turnFaceUp() {
        faceUp = true;
    }

    public void turnFaceDown() {
        faceUp = false;
    }

    /**
     * Returns the visible card representation.
     */
    @Override
    public String toString() {
        if (!faceUp) {
            return "[##]";
        }

        return "[" + rank.getDisplayName() + " of " + suit.getDisplayName() + "]";
    }
}
