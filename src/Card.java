public class Card {
    private static String[] SUITS = { "S", "H", "C", "D" };
    
    int _suit, _number;
    public Card(int number, int suit) {
        _suit = suit;
        _number = number;
    }
    
    public String toString() {
        return "" + (_number + 1) + SUITS[_suit];
    }
    
    public int getNumber() { return _number; } 
    public String getSuit() { return SUITS[_suit]; }
}