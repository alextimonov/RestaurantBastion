package ua.goit.timonov.enterprise.web.validate;

import org.apache.commons.lang3.StringUtils;
import ua.goit.timonov.enterprise.model.Menu;

/**
 * Validator for Menu class
 */
public class MenuValidate {
    private String nameLabel;

    public String getNameLabel() {
        return nameLabel;
    }

    public boolean isValid(Menu menu) {
        boolean result = true;
        if (StringUtils.isBlank(menu.getName())) {
            result = false;
            nameLabel = "You should input some name";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuValidate)) return false;

        MenuValidate that = (MenuValidate) o;

        return nameLabel != null ? nameLabel.equals(that.nameLabel) : that.nameLabel == null;

    }

    @Override
    public int hashCode() {
        return nameLabel != null ? nameLabel.hashCode() : 0;
    }
}
