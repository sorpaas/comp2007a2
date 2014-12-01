public class UserPlayer extends Player {
    public void play(Player nextPlayer, Random rand) {
        Card picked = nextPlayer.pickOne(rand);
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
}