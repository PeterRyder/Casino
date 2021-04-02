public class BlackjackGame implements Game {

    private final Deck deck;
    private final Hand playerHand;
    private final Hand dealerHand;

    public BlackjackGame() {
        deck = new Deck(408);
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
    }

    public void DealToPlayer() {
        this.playerHand.AddCard(deck.DrawCard());
    }

    public void DealToDealer() {
        this.dealerHand.AddCard(deck.DrawCard());
    }
}
