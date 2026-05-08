package com.skills4it.blackjack;

import com.skills4it.blackjack.enums.Rank;
import com.skills4it.blackjack.enums.Suit;
import com.skills4it.blackjack.model.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    void newCard_shouldBeFaceDown() {
        // arrange
        Card card = new Card(Suit.HEARTS, Rank.ACE);

        // act
        boolean actual = card.isFaceUp();

        // assert
        assertFalse(actual);
    }

    @Test
    void getSuit_shouldReturnHiddenSymbol_whenCardIsFaceDown() {
        // arrange
        Card card = new Card(Suit.HEARTS, Rank.ACE);
        String expected = "#";

        // act
        String actual = card.getSuit();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getPointValue_shouldReturnZero_whenCardIsFaceDown() {
        // arrange
        Card card = new Card(Suit.HEARTS, Rank.ACE);
        int expected = 0;

        // act
        int actual = card.getPointValue();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getPointValue_shouldReturnEleven_forFaceUpAce() {
        // arrange
        Card card = new Card(Suit.HEARTS, Rank.ACE);
        int expected = 11;
        card.turnFaceUp();

        // act
        int actual = card.getPointValue();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void flip_shouldChangeFaceUpState() {
        // arrange
        Card card = new Card(Suit.CLUBS, Rank.KING);

        // act
        card.flip();

        // assert
        assertTrue(card.isFaceUp());
    }
}
