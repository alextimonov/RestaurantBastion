package ua.goit.timonov.enterprise.model;

/**
 * Available positions for employees
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
