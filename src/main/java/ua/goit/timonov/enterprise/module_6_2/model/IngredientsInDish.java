package ua.goit.timonov.enterprise.module_6_2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Alex on 18.08.2016.
 */
@Entity
@Table(name = "ingredient_to_dish")
public class IngredientsInDish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(strategy = "increment", name = "increment")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "weight")
    private int ingredientWeight;

    public IngredientsInDish() {

    }

    public IngredientsInDish(Dish dish, Ingredient ingredient, int ingredientWeight) {
        this.dish = dish;
        this.ingredient = ingredient;
        this.ingredientWeight = ingredientWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getIngredientWeight() {
        return ingredientWeight;
    }

    public void setIngredientWeight(int ingredientWeight) {
        this.ingredientWeight = ingredientWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientsInDish)) return false;

        IngredientsInDish that = (IngredientsInDish) o;

        if (ingredientWeight != that.ingredientWeight) return false;
        if (dish != null ? !dish.equals(that.dish) : that.dish != null) return false;
        return ingredient != null ? ingredient.equals(that.ingredient) : that.ingredient == null;

    }

    @Override
    public int hashCode() {
        int result = dish != null ? dish.hashCode() : 0;
        result = 31 * result + (ingredient != null ? ingredient.hashCode() : 0);
        result = 31 * result + ingredientWeight;
        return result;
    }

    @Override
    public String toString() {
        return "IngredientsInDish{" +
                "id=" + id +
                ", dish=" + dish.getName() +
                ", ingredient=" + ingredient.getName() +
                ", ingredientWeight=" + ingredientWeight +
                '}';
    }
}
