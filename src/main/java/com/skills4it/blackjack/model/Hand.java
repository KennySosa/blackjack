package com.skills4it.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Hand has many Cards.
 *
 * Responsibilities:
 * - store cards dealt to it
 * - calculate the total hand value
 * - determine if the hand is busted
 */
public class Hand {
    private final ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * A Card is dealt to the Hand.
     * The Hand is responsible for storing that card.
     */
    public void deal(Card card) {
        if (card == null) {
            return;
        }

        card.turnFaceUp();
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    /**
     * Calculates the best Blackjack hand value.
     *
     * Ace is worth 11 by default.
     * If the hand would bust, one or more Aces can count as 1.
     */
    public int getValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : cards) {
            value += card.getPointValue();

            if (card.isAce()) {
                aceCount++;
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String display() {
        StringBuilder builder = new StringBuilder();

        for (Card card : cards) {
            builder.append(card).append(" ");
        }

        builder.append("Value: ").append(getValue());

        if (isBust()) {
            builder.append(" BUST");
        }

        return builder.toString();
    }
}
