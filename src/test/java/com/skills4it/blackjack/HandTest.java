package com.skills4it.blackjack;

import com.skills4it.blackjack.enums.Rank;
import com.skills4it.blackjack.enums.Suit;
import com.skills4it.blackjack.models.Card;
import com.skills4it.blackjack.models.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    @Test
    void deal_shouldAddCardToHand() {
        // arrange
        Hand hand = new Hand();
        Card card = new Card(Suit.HEARTS, Rank.TEN);
        int expectedSize = 1;

        // act
        hand.deal(card);

        // assert
        assertEquals(expectedSize, hand.getSize());
    }

    @Test
    void getValue_shouldAddCardValues() {
        // arrange
        Hand hand = new Hand();
        int expectedValue = 17;

        // act
        hand.deal(new Card(Suit.HEARTS, Rank.TEN));
        hand.deal(new Card(Suit.SPADES, Rank.SEVEN));
        int actualValue = hand.getValue();

        // assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getValue_shouldCountAceAsElevenByDefault() {
        // arrange
        Hand hand = new Hand();
        int expectedValue = 18;

        // act
        hand.deal(new Card(Suit.HEARTS, Rank.ACE));
        hand.deal(new Card(Suit.SPADES, Rank.SEVEN));
        int actualValue = hand.getValue();

        // assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getValue_shouldCountAceAsOne_whenElevenWouldBust() {
        // arrange
        Hand hand = new Hand();
        int expectedValue = 18;

        // act
        hand.deal(new Card(Suit.HEARTS, Rank.ACE));
        hand.deal(new Card(Suit.SPADES, Rank.KING));
        hand.deal(new Card(Suit.CLUBS, Rank.SEVEN));
        int actualValue = hand.getValue();

        // assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void isBust_shouldReturnTrue_whenValueIsOver21() {
        // arrange
        Hand hand = new Hand();

        // act
        hand.deal(new Card(Suit.HEARTS, Rank.KING));
        hand.deal(new Card(Suit.SPADES, Rank.QUEEN));
        hand.deal(new Card(Suit.CLUBS, Rank.TWO));

        // assert
        assertTrue(hand.isBust());
    }

    @Test
    void isBlackjack_shouldReturnTrue_forAceAndTenPointCard() {
        // arrange
        Hand hand = new Hand();

        // act
        hand.deal(new Card(Suit.HEARTS, Rank.ACE));
        hand.deal(new Card(Suit.SPADES, Rank.KING));

        // assert
        assertTrue(hand.isBlackjack());
    }
}
