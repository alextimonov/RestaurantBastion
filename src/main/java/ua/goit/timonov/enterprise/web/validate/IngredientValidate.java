package ua.goit.timonov.enterprise.web.validate;

import org.apache.commons.lang3.StringUtils;
import ua.goit.timonov.enterprise.model.Ingredient;

/**
 * Created by Alex on 20.09.2016.
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
}
