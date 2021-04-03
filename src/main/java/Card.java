package main.java;

public class Card {

    private final Suit suit;
    private final int face;

    public Card(Suit suit, int face) {
        this.suit = suit;
        this.face = face;
    }

    public int getValue() {
        return face;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", face=" + face +
                '}';
    }
}
