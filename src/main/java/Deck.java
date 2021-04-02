package main.java;

import java.util.Random;

public class Deck {

    private final int cardAmount;

    public Deck(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    public Card DrawCard() {
        int face = new Random().nextInt(12) + 1;
        int suitInt = new Random().nextInt(3) + 1;
        Suit suit = Suit.values()[suitInt];
        return new Card(suit, face);
    }
}
