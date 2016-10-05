package ua.goit.timonov.enterprise.web.validate;

import org.apache.commons.lang3.StringUtils;
import ua.goit.timonov.enterprise.model.Ingredient;

/**
 * Validator for Ingredient class
 */
public class IngredientValidate {

    private String nameLabel;
    private String amountLabel;

    public String getNameLabel() {
        return nameLabel;
    }

    public String getAmountLabel() {
        return amountLabel;
    }

    public boolean isValid(Ingredient item) {
        boolean result = true;
        if (StringUtils.isBlank(item.getName())) {
            result = false;
            nameLabel = "You should input some name";
        }
        if (item.getAmount() <= 0) {
            result = false;
            amountLabel = "You should input positive value";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientValidate)) return false;

        IngredientValidate that = (IngredientValidate) o;

        if (nameLabel != null ? !nameLabel.equals(that.nameLabel) : that.nameLabel != null) return false;
        return amountLabel != null ? amountLabel.equals(that.amountLabel) : that.amountLabel == null;

    }

    @Override
    public int hashCode() {
        int result = nameLabel != null ? nameLabel.hashCode() : 0;
        result = 31 * result + (amountLabel != null ? amountLabel.hashCode() : 0);
        return result;
    }
}
