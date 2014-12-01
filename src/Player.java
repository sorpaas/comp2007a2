import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Player {
    String _name;
    ArrayList<Card> _cards = new ArrayList<Card>();
    
    public void play(Player nextPlayer) {
        Card picked = nextPlayer.pickOne();
        this.play(nextPlayer, picked);
    }
    
    public void play(Player nextPlayer, Card picked) {
        if(this.isHoldingNumber(picked.getNumber())) {
            this.removeWithNumber(picked.getNumber());
        } else {
            nextPlayer.addCard(picked);
        }
        
        if(nextPlayer.count() == 0) {
            System.out.println(nextPlayer.toString() + ": I gave a card and I am done!");
        }
        
        if(this.count() == 0) {
            System.out.println(this.toString() + ": I found my last pair and I am done!");
        }
    }
    
    public void discard() {
        for(Card card1 : _cards) {
            for(Card card2 : _cards) {
                if(card1 != card2 && card1.getNumber() == card2.getNumber()) {
                    _cards.remove(card1);
                    _cards.remove(card2);
                    discard();
                    return;
                }
            }
        }
    }
    
    public boolean isHoldingNumber(int number) {
        for(int i = 0; i < _cards.size(); i++) {
            if ( _cards.get(i).getNumber() == number) { return true; }
        }
        return false;
    }
    
    public Card pickOne() {
        return pickOne(OldMaid.RAND.nextInt(_cards.size()));
    }
    
    public Card pickOne(int i) {
        Card picked = _cards.get(i);
        _cards.remove(i);
        return picked;
    }
    
    public void removeWithNumber(int number) {
        for(int i = 0; i < _cards.size(); i++) {
            if ( _cards.get(i).getNumber() == number) { _cards.remove(i); return; }
        }
    }
    
    public void addCard(Card card) {
        _cards.add(card);
    }
    
    public void showCards() {
        String result = this.toString();
        result += ": ";
        for(int i = 0; i < _cards.size(); i++) {
            result += String.format("%5s", _cards.get(i).toString());
            result += " ";
        }
        System.out.println(result);
    } //This is a debug function, why don't you tell others?
    
    public int count() {
        return _cards.size();
    }
    
    public String toString() {
        return _name;
    }
    
    public Player(String name) {
        _name = name;
    }
}