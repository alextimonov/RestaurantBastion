package ua.goit.timonov.enterprise.module_6_2.view.menus;

/**
 * Created by Alex on 03.08.2016.
 */
public abstract class ConsoleMenuItem {

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

    public abstract void  run();
}