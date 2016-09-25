package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.model.Job;
import ua.goit.timonov.enterprise.module_6_2.model.Position;

import java.util.GregorianCalendar;

/**
 * Created by Alex on 21.09.2016.
 */
public class ObjectsFactory {

    public Dish makeDishSalad() {
        Dish salad = new Dish();
        salad
                .append("salad", "light salad with delicious vegetables")
                .append(250)
                .append(45.0F);
        return salad;
    }

    public Dish makeDishRiceSoup() {
        Dish soup = new Dish();
        soup
                .append("rice soup", "created by standard recipe")
                .append(300)
                .append(50.0F);
        return soup;
    }

    public Dish makeDishNewPlov() {
        Dish plov = new Dish();
        plov
                .append("new plov", "rice with meat")
                .append(350)
                .append(60.0F);
        return plov;
    }

    public Dish makeDishSalmon() {
        Dish salmon = new Dish();
        salmon
                .append("salmon", "fried salmon")
                .append(300)
                .append(80.0F);
        return salmon;
    }

    public Dish makeDishFriedEggs() {
        Dish friedEggs = new Dish();
        friedEggs
                .append("fried eggs", "fried eggs with bacon")
                .append(175)
                .append(40.0F);
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
}
