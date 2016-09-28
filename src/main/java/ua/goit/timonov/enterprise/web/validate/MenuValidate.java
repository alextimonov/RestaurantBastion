package ua.goit.timonov.enterprise.web.validate;

import org.apache.commons.lang3.StringUtils;
import ua.goit.timonov.enterprise.model.Menu;

/**
 * Created by Alex on 20.09.2016.
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
}
