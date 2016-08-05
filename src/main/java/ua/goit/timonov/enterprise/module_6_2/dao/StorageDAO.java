package ua.goit.timonov.enterprise.module_6_2.dao;

import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;

import java.util.List;

/**
 * Created by Alex on 01.08.2016.
 */
public interface StorageDAO {

    void add(Ingredient ingredient);

    void delete(int id);

    void delete(String ingredientName);

    void changeAmount(String ingredientName, int difference);

    Ingredient search(int id);

    Ingredient search(String ingredientName);

    List<Ingredient> getAll();

    List<Ingredient> getTerminatingIngredients(int limit);
}