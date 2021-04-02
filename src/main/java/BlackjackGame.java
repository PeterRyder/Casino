package main.java;

public class BlackjackGame extends Game {

    public BlackjackGame(int cardAmount) {
        super(cardAmount);
    }

    @Override
    public void InitialDeal() {
        this.playerHand.AddCard(deck.DrawCard());
        this.playerHand.AddCard(deck.DrawCard());

        this.dealerHand.AddCard(deck.DrawCard());
        this.dealerHand.AddCard(deck.DrawCard());
    }

    @Override
    public void CheckState() {

    }

    @Override
    public void End() {

    }
}
