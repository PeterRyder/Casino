public class Deck {

    private final int cardAmount;

    public Deck(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    public Card DrawCard() {
        return new Card();
    }
}
