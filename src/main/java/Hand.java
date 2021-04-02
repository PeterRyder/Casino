package main.java;

import java.util.ArrayList;

public class Hand {

    private final ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<Card>();
    }

    public void AddCard(Card card) {
        this.cards.add(card);
    }

    public void RemoveCard(Card card) {
        this.cards.remove(card);
    }

    public ArrayList<Card> Cards() {
        return cards;
    }
}
