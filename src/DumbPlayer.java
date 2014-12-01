public class DumbPlayer extends Player {
    public void discard() {
        if(OldMaid.RAND.nextFloat() <= 0.7) {
            super.discard();
        }
    }
}