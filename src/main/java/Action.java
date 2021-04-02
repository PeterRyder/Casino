package main.java;

public abstract class Action {

    public static String ActionType() {
        throw new IllegalStateException("ActionType isn't set up in subclass");
    }

    public abstract void Execute(ActionContext context);
}
