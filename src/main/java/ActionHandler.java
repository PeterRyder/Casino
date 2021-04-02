package main.java;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Locale;

public class ActionHandler {

    private final HashMap<String, Class<? extends Action>> actions;

    public ActionHandler() {
        actions = new HashMap<>();
    }

    public void subscribe(Class<? extends Action> action) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] params = null;
        this.actions.put((String) action.getMethod("ActionType").invoke(null), action);
        System.out.printf("Registered action: %s\n", action.getCanonicalName());
        System.out.printf("Registered actions: %s\n", actions);
    }

    public void dispatch(ActionContext context, String actionType) throws InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.actions.get(actionType.toLowerCase(Locale.ROOT)).getDeclaredConstructor().newInstance().Execute(context);
        System.out.printf("Dispatched action: %s\n", actionType);
    }
}
