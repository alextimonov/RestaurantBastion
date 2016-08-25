package ua.goit.timonov.enterprise.module_6_2.model;

/**
 * Abstract DB item with common fields
 */

public class DbItem {
    /* unique id in the DB table */
    protected int id;

    /* item's name */
    protected String name;

    public DbItem() {
    }

    public DbItem(String name) {
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DbItem dbItem = (DbItem) o;

        return name != null ? name.equals(dbItem.name) : dbItem.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
