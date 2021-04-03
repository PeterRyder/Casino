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

    public int getTotal() {
        int total = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            int cardTotal = cards.get(i).getValue();
            if (cardTotal == 11 || cardTotal == 12 || cardTotal == 13) {
                cardTotal = 10;
            }
            total += cardTotal;
        }
        return total;
    }

    public ArrayList<Card> Cards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
