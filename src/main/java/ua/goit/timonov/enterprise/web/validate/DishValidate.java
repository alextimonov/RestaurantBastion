package ua.goit.timonov.enterprise.web.validate;

import org.apache.commons.lang3.StringUtils;
import ua.goit.timonov.enterprise.model.Dish;

/**
 * Created by Alex on 19.09.2016.
 */
public class DishValidate {

    private String nameLabel;
    private String descriptionLabel;
    private String weightLabel;
    private String costLabel;

    public String getNameLabel() {
        return nameLabel;
    }

    public String getDescriptionLabel() {
        return descriptionLabel;
    }

    public String getWeightLabel() {
        return weightLabel;
    }

    public String getCostLabel() {
        return costLabel;
    }

    public boolean isValid(Dish dish) {
        boolean result = true;
        if (StringUtils.isBlank(dish.getName())) {
            result = false;
            nameLabel = "You should input some name";
        }
        if (StringUtils.isBlank(dish.getDescription())) {
            result = false;
            descriptionLabel = "You should input some description";
        }
        if (dish.getWeight() <= 0) {
            result = false;
            weightLabel = "You should input positive value";
        }
        if (dish.getCost() <= 0) {
            result = false;
            costLabel = "You should input positive value";
        }
        return result;
    }
}
