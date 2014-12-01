import java.util.Random;

public class DeckOfCards {
    private Card[] _cards = new Card[52];
    
    public DeckOfCards() {
        for(int number = 0; number < (52 / 4); number++) {
            for(int suit = 0; suit < 4; suit++) {
                _cards[number * 4 + suit] = new Card(number, suit);
            }
        }
    }
    
    public void shuffle(Random r) {
        Card[] newDeck = new Card[52];
        for(int i = 0; i < 52; i++) {
            int newI = -1; //Just make sure the below line works.
            do { newI = r.nextInt(52); } while(_cards[newI] == null);
            newDeck[i] = _cards[newI];
            _cards[newI] = null;
        }
        _cards = newDeck;
    }
    
    public void distribute(Player[] players) {
        int nextPlayerI = 0;
        for(int i = 0; i < 51; i++) {
            players[nextPlayerI].addCard(this.getCard(i));
            nextPlayerI = (nextPlayerI + 1) % players.length;
        }
    }
    
    public Card getCard(int i) { return _cards[i]; }
    public Card getOldMaid() { return this.getCard(51); }
}