package com.skills4it.blackjack.enums;

public enum PlayerRank {


    GOLD("Gold"),
    SILVER("Silver"),
    BRONZE("Bronze");

    private final String displayName;

    PlayerRank(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
