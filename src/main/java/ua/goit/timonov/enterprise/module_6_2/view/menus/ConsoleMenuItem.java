package ua.goit.timonov.enterprise.module_6_2.view.menus;

/**
 * Console menu's item
 */
public abstract class ConsoleMenuItem {

    /* item's text */
    private String title;

    public ConsoleMenuItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * runs task mapped to menu's item
     */
    public abstract void run();
}