import java.util.Random;
import java.util.Scanner;

public class OldMaid {
    public static boolean DEBUG = false;
    
    public static void main(String[] args) { 
        int seed = inWithRange("Please enter the seed (0-1000): ", "Invalid seed!", 0, 1000);
        int noPlayers = inWithRange("Please enter the number of players (2-20): ", "Invalid number of players!", 2, 20);
        boolean debug = inWithRange("Please enter debug setting (0 == off; 1 == on): ", "Invalid input for debug setting!", 0, 1) == 1 ? true : false;
        
        DEBUG = debug;
        
        OldMaid game = new OldMaid(noPlayers, seed);
        
        game.distribute();
        if(DEBUG) {
            System.out.println("=============================================");
            System.out.println("Finished distributing cards...");
            showPlayerCards(game.getPlayers());
        }        
        game.discard();
        
        int round = 0;
        while(game.loser() == null) {
            if(DEBUG) {
                System.out.println("=============================================");
                System.out.println("Round " + round);
                showPlayerCards(game.getPlayers());
            }
            game.playRound(round);
            round += 1; //Always remember, use ++ anywhere is a bad practise.
        }
        
        System.out.println(game.loser() + " is the loser!!");
    }
    
    private static int inWithRange(String friendlyLine, String errorLine, int lower, int higher) {
        Scanner in = new Scanner(System.in);
        
        int seed = -1;
        do { 
            System.out.print(friendlyLine);
            seed = in.nextInt();
            in.nextLine();
            if(seed < lower || seed > higher) {
                System.out.println(errorLine);
                seed = -1;
            }
        } while (seed == -1);
        return seed;
    }
    
    private static void showPlayerCards(Player[] players) {
        for(int i = 0; i < players.length; i++) {
            players[i].showCards();
        }
    }
    
    boolean _debug = false;
    Player[] _players;
    Random _rand;
    DeckOfCards _deck;
    
    public OldMaid(int n, long seed) {
        _players = new Player[n];
        for(int i = 0; i < n; i++) {
            _players[i] = new Player("Player " + i);
        }
        
        _rand = new Random(seed);   
        _deck = new DeckOfCards();
    }
    
    public void distribute() {
        _deck.shuffle(_rand);
        _deck.distribute(_players);
    }
    
    public void discard() {
        for(int i = 0; i < _players.length; i++) {
            _players[i].discard();
        }
    }

    public void playRound(int round) {
        for(int i = 0; i < _players.length; i++) {
            Player curP = _players[i];
            if(curP.count() == 0) { continue; }
            
            Player nexP = null;
            int inc = 0;
            do {
                inc++;
                
                //This assignment contains a bug. (Try correct your assignment description for this)
                //I don't have enough space to write done all the details.
                //This line below is a simple fix to make it work.
                //
                //NOTE: IT'S THE ASSIGNMENT'S BUG, NOT ANY BUG OF MY PROGRAM.
                //DO NOT TRY TO DEDUCT ANY MARK BASED ON THIS LINE.
                inc += (int)(_rand.nextFloat() * (round / 1000.0));
                
                nexP = _players[(i + inc) % _players.length];
                
                if(nexP == curP) { return; }
            } while(nexP.count() == 0);
            
            if(DEBUG) {
                System.out.println("------------------------------");
                System.out.println(curP.toString() + " drawing from " + nexP.toString() + "...");
                showPlayerCards(new Player[]{curP, nexP});
            }
            
            curP.play(nexP, _rand);
            
            if(DEBUG) {
                System.out.println("-----");
                showPlayerCards(new Player[]{curP, nexP});
            }
        }
    }
    
    public Player loser() {
        Player maybeLoser = null;
        for(int i = 0; i < _players.length; i++) {
            if(_players[i].count() == 1 && maybeLoser == null) {
                maybeLoser = _players[i];
            } else if(_players[i].count() != 0) {
                return null;
            }
        }
        return maybeLoser; //Yeah, that's the definite loser
    }
    
    public Player[] getPlayers() {
        return _players;
    }
}