public class UserPlayer extends Player {
    public void play(Player nextPlayer) {
        int i = OldMaid.inWithRange("It's your turn man, pick up a number for the next player (0-" + (nextPlayer.count() - 1) + "): ",
            "Oh damn, you picked the wrong number. Try again? \nOr if you just get bored, hit Control+C.", 0, (nextPlayer.count() - 1));
        Card picked = nextPlayer.pickOne(i);
        super.play(nextPlayer, picked);
    }
}