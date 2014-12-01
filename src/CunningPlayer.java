public class CunningPlayer extends Player {
    public void play(Player nextPlayer) {
        super.play(nextPlayer);
        if(OldMaid.RAND.nextFloat() > 0.7) {
            super.play(nextPlayer);
        }
    }
}