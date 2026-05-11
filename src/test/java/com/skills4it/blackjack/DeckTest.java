package com.skills4it.blackjack;

import com.skills4it.blackjack.models.Card;
import com.skills4it.blackjack.models.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void newDeck_shouldContain52Cards() {
        // arrange
        Deck deck = new Deck();
        int expectedSize = 52;

        // act
        int actualSize = deck.getSize();

        // assert
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void deal_shouldReturnCard_andReduceDeckSize() {
        // arrange
        Deck deck = new Deck();
        int expectedSize = 51;

        // act
        Card card = deck.deal();
        int actualSize = deck.getSize();

        // assert
        assertNotNull(card);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void deal_shouldReturnNull_whenDeckIsEmpty() {
        // arrange
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.deal();
        }

        // act
        Card card = deck.deal();

        // assert
        assertNull(card);
    }
}
