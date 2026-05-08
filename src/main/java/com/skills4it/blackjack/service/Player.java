package com.skills4it.blackjack.service;

import com.skills4it.blackjack.enums.BettingOption;
import com.skills4it.blackjack.enums.PlayerRank;
import com.skills4it.blackjack.model.Card;
import com.skills4it.blackjack.model.Hand;

/**
 * A Player has a Hand.
 *
 * Responsibilities:
 * - know the player's name
 * - store the player's hand
 */
public class Player {
    private final String name;
    private final Hand hand;
    private int score;
    private PlayerRank rank;
    private BettingOption bettingOption;
    ///------------------------added score and playerrank

    public Player(String name) {
        if (name == null || name.isBlank()) {
            this.name = "Unknown Player";
        } else {
            this.name = name.trim();
        }

        this.hand = new Hand();
    }

    public BettingOption getBettingOption() {
        return bettingOption;
    }

    public void setBettingOption(BettingOption bettingOption) {
        this.bettingOption = bettingOption;
    }

    public void setRank(PlayerRank rank) {
        this.rank = rank;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PlayerRank getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void receiveCard(Card card) {
        hand.deal(card);
    }

    public int getScore() {
        return hand.getValue();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    @Override
    public String toString() {

        String rankName = "Unranked";

        if (rank != null) {
            rankName = rank.getDisplayName();
        }

        String betInfo = "No Bet";

        if (bettingOption != null) {
            betInfo =
                    bettingOption.getDisplayName()
                            + " ($"
                            + bettingOption.getAmount()
                            + ")";
        }

        return
                "Player: " + name +
                        " | Score: " + getScore() +
                        " | Rank: " + rankName +
                        " | Bet: " + betInfo;
    }
}
