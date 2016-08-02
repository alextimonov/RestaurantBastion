package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.List;

/**
 * Created by Alex on 01.08.2016.
 */
public interface StorageDAO {

    void add(Ingredient ingredient);

    void delete(String ingredientName);

    void changeAmount(String ingredientName, int difference);

    Ingredient find(String ingredientName);

    List<Ingredient> getAll();

    List<Ingredient> getTerminatingIngredients(int limit);
}