package ua.goit.timonov.enterprise.module_9.web.validate;

import org.apache.commons.lang3.StringUtils;
import ua.goit.timonov.enterprise.module_6_2.model.IngredientsInDish;

/**
 * Created by Alex on 25.09.2016.
 */
public class ItemInDishValidate {
    private String nameLabel;
    private String weightLabel;

    public String getNameLabel() {
        return nameLabel;
    }

    public String getWeightLabel() {
        return weightLabel;
    }

    public boolean isValid(IngredientsInDish item) {
        boolean result = true;
        if (StringUtils.isBlank(item.getIngredient().getName())) {
            result = false;
            nameLabel = "You should input some name";
        }
        if (item.getIngredientWeight() <= 0) {
            result = false;
            weightLabel = "You should input positive value";
        }
        return result;
    }
}
