package ua.goit.timonov.enterprise.module_6_2.view.menus;

/**
 * Console menu's item
 */
public class ConsoleMenuItem {

    /* item's text */
    private String title;

    private Runnable callback;

    public ConsoleMenuItem() {
    }

    public ConsoleMenuItem(String title, Runnable callback) {
        this.title = title;
        this.callback = callback;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Runnable getCallback() {
        return callback;
    }

}