package main.java.blackjack.actions;

import main.java.Action;
import main.java.ActionContext;

public class Stay extends Action {

    public static String ActionType() {
        return "stay";
    }

    @Override
    public void Execute(ActionContext context) {
        context.game.ShowPlayerHand();
    }
}
