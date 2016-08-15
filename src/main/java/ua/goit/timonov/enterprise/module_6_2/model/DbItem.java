package ua.goit.timonov.enterprise.module_6_2.model;

/**
 * Abstract DB item with common fields
 */

public class DbItem {
    /* unique id in the DB table */
    protected int id;

    /* item's name */
    protected String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
