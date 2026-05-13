"Hi this is cool" 
# Blackjack - Maven Project

This project is based on **Module 5: Class Interactions**.

It demonstrates:

- Class relationships
- Has-A relationships
- Loose coupling
- High cohesion
- `ArrayList<Card>`
- Object collaboration between `Deck`, `Hand`, `Player`, and `BlackjackGame`

## Project structure

```text
blackjack/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── skills4it/
│   │               └── blackjack/
│   │                   ├── BlackjackGame.java
│   │                   ├── Card.java
│   │                   ├── Deck.java
│   │                   ├── Hand.java
│   │                   ├── MainApp.java
│   │                   ├── Player.java
│   │                   ├── Rank.java
│   │                   └── Suit.java
│   └── test/
│       └── java/
│           └── com/
│               └── skills4it/
│                   └── blackjack/
│                       ├── CardTest.java
│                       ├── DeckTest.java
│                       └── HandTest.java
```

## How to run

From the project root:

```bash
mvn clean test
mvn exec:java
```

## OOP design

### Has-A relationships

```text
Deck has many Cards
Hand has many Cards
Player has one Hand
BlackjackGame has one Deck
BlackjackGame has many Players
```

### Important teaching idea

The `MainApp` starts the application, but it does not contain the card logic.

- `Card` knows its own suit, rank, face-up state, and point value.
- `Deck` creates, shuffles, and deals cards.
- `Hand` stores cards and calculates total value.
- `Player` has a name and a hand.
- `BlackjackGame` manages the flow of the game.

## Student extension ideas

1. Add a dealer.
2. Let players choose Hit or Stay.
3. Add betting.
4. Add multiple rounds.
5. Track wins and losses.
6. Add better Ace logic for multiple Aces.
7. Add colored console output.
