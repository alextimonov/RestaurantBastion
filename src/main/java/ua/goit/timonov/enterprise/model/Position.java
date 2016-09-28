package ua.goit.timonov.enterprise.model;

/**
 * Created by Alex on 09.08.2016.
 */
public enum Position {
    DIRECTOR,
    MANAGER,
    COOK,
    COOK_ASSISTANT,
    HEAD_WAITER,
    WAITER,
    SECURITY,
    CLEANER;

    public static Position byName(String name) {
        if (name == null) return null;
        Position[] positions = Position.values();
        for (Position position : positions) {
            if (name.equals(position.name()))
                return position;
        }
        return null;
    }
}
