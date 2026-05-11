package com.skills4it.blackjack.models;

import com.skills4it.blackjack.enums.Rank;
import com.skills4it.blackjack.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Deck has many Cards.
 *
 * Responsibilities:
 * - create all 52 cards
 * - shuffle cards
 * - deal cards one at a time
 * - know how many cards are left
 *
 * Notice: Deck does not know anything about Hand.
 * It only returns a Card when deal() is called.
 */
public class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Deals the top card from the deck.
     * The card is removed from the deck, because it has now moved somewhere else.
     */
    public Card deal() {
        if (cards.isEmpty()) {
            return null;
        }

        return cards.remove(0);
    }

    public int getSize() {
        return cards.size();
    }

    /**
     * Returns a read-only view of the cards.
     * Useful for tests and demos, but external classes cannot modify the deck.
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
