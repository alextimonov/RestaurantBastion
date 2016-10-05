package ua.goit.timonov.enterprise.web.validate;

import org.apache.commons.lang3.StringUtils;
import ua.goit.timonov.enterprise.model.Dish;

/**
 * Validator for Dish class
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DishValidate)) return false;

        DishValidate that = (DishValidate) o;

        if (nameLabel != null ? !nameLabel.equals(that.nameLabel) : that.nameLabel != null) return false;
        if (descriptionLabel != null ? !descriptionLabel.equals(that.descriptionLabel) : that.descriptionLabel != null)
            return false;
        if (weightLabel != null ? !weightLabel.equals(that.weightLabel) : that.weightLabel != null) return false;
        return costLabel != null ? costLabel.equals(that.costLabel) : that.costLabel == null;

    }

    @Override
    public int hashCode() {
        int result = nameLabel != null ? nameLabel.hashCode() : 0;
        result = 31 * result + (descriptionLabel != null ? descriptionLabel.hashCode() : 0);
        result = 31 * result + (weightLabel != null ? weightLabel.hashCode() : 0);
        result = 31 * result + (costLabel != null ? costLabel.hashCode() : 0);
        return result;
    }
}
