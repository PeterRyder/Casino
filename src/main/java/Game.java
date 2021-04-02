package main.java;

public abstract class Game {

    protected Deck deck;
    protected Hand playerHand;
    protected Hand dealerHand;

    public Game(int cardAmount) {
        this.deck = new Deck(cardAmount);
        this.playerHand = new Hand();
        this.dealerHand = new Hand();

        this.InitialDeal();
    }

    public abstract void InitialDeal();

    public abstract void CheckState();

    public abstract void End();

    public void DealToPlayer() {
        this.playerHand.AddCard(deck.DrawCard());
    }
    public void DealToDealer() {
        this.dealerHand.AddCard(deck.DrawCard());
    }

    public void ShowPlayerHand() { System.out.println("Player: " + this.playerHand); }
    public void ShowDealerHand() { System.out.println("Dealer: " + this.dealerHand); }
}
