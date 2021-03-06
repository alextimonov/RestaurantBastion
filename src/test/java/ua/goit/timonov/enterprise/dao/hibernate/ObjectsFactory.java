package ua.goit.timonov.enterprise.dao.hibernate;

import ua.goit.timonov.enterprise.model.*;

import java.util.GregorianCalendar;

/**
 * Object's factory for tests
 */
public class ObjectsFactory {

    public Dish makeDishSalad() {
        Dish salad = new Dish();
        salad
                .append("salad", "light salad with delicious vegetables")
                .append(250, 45.0F);
        return salad;
    }

    public Dish makeDishRiceSoup() {
        Dish soup = new Dish();
        soup
                .append("rice soup", "created by standard recipe")
                .append(300, 50.0F);
        return soup;
    }

    public Dish makeDishNewPlov() {
        Dish plov = new Dish();
        plov
                .append("new plov", "rice with meat")
                .append(350, 60.0F);
        return plov;
    }

    public Dish makeDishSalmon() {
        Dish salmon = new Dish();
        salmon
                .append("salmon", "fried salmon")
                .append(300, 80.0F);
        return salmon;
    }

    public Dish makeDishFriedEggs() {
        Dish friedEggs = new Dish();
        friedEggs
                .append("fried eggs", "fried eggs with bacon")
                .append(175, 40.0F);
        return friedEggs;
    }

    public Employee makeEmployeeWhite() {
        Employee mrWhite = new Employee();
        mrWhite
                .append("John", "White")
                .append(new GregorianCalendar(1990, 4, 30).getTime())
                .append(new Job(Position.MANAGER))
                .append(75000F);
        return mrWhite;
    }

    public Employee makeEmployeeBlack() {
        Employee mrBlack = new Employee();
        mrBlack
                .append("Steven", "Black")
                .append(new GregorianCalendar(1998, 5, 20).getTime())
                .append(new Job(Position.WAITER))
                .append(35000F);
        return mrBlack;
    }

    public Employee makeEmployeeRed() {
        Employee mrRed = new Employee();
        mrRed
                .append("Peter", "Red")
                .append(new GregorianCalendar(1999, 7, 12).getTime())
                .append(new Job(Position.COOK))
                .append(50000F);
        return mrRed;
    }

    public Waiter makeWaiterBlack() {
        return new Waiter(makeEmployeeBlack());
    }

    public Waiter makeWaiterGreen() {
        Employee mrGreen = new Employee();
        mrGreen
                .append("Tom", "Green")
                .append(new GregorianCalendar(1997, 5, 25).getTime())
                .append(new Job(Position.WAITER))
                .append(37000F);
        return new Waiter(mrGreen);
    }
}
