package main.java.blackjack.actions;

import main.java.Action;
import main.java.ActionContext;

public class Hit extends Action {

    public static String ActionType() {
        return "hit";
    }

    @Override
    public void Execute(ActionContext context) {
        context.game.DealToPlayer();
        context.game.ShowPlayerHand();
    }
}
