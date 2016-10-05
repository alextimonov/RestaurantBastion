package ua.goit.timonov.enterprise.web.validate;

import org.apache.commons.lang3.StringUtils;
import ua.goit.timonov.enterprise.model.Position;
import ua.goit.timonov.enterprise.web.EmployeeView;

/**
 * Validator for Employee class
 */
public class EmployeeValidate {

    private String nameLabel;
    private String surnameLabel;
    private String birthdayLabel;
    private String positionLabel;
    private String salaryLabel;

    public String getNameLabel() {
        return nameLabel;
    }

    public String getSurnameLabel() {
        return surnameLabel;
    }

    public String getBirthdayLabel() {
        return birthdayLabel;
    }

    public String getPositionLabel() {
        return positionLabel;
    }

    public String getSalaryLabel() {
        return salaryLabel;
    }

    public boolean isValid(EmployeeView employeeView) {
        boolean result = true;
        if (StringUtils.isBlank(employeeView.getName())) {
            result = false;
            nameLabel = "You should input some name";
        }
        if (StringUtils.isBlank(employeeView.getSurname())) {
            result = false;
            surnameLabel = "You should input some surname";
        }
        if (employeeView.getBirthday() == null) {
            result = false;
            birthdayLabel = "You should input date properly";
        }
        String inputtedPosition = employeeView.getPosition();
        if (isNotInPositionValues(inputtedPosition)) {
            result = false;
            positionLabel = "You should input some existing position";
        }
        if (employeeView.getSalary() <= 0) {
            result = false;
            salaryLabel = "You should input positive value";
        }
        return result;
    }

    private boolean isNotInPositionValues(String inputtedPosition) {
        Position[] positions = Position.values();
        for (Position position : positions) {
            if (position.name().equalsIgnoreCase(inputtedPosition))
                return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeValidate)) return false;

        EmployeeValidate that = (EmployeeValidate) o;

        if (nameLabel != null ? !nameLabel.equals(that.nameLabel) : that.nameLabel != null) return false;
        if (surnameLabel != null ? !surnameLabel.equals(that.surnameLabel) : that.surnameLabel != null) return false;
        if (birthdayLabel != null ? !birthdayLabel.equals(that.birthdayLabel) : that.birthdayLabel != null)
            return false;
        if (positionLabel != null ? !positionLabel.equals(that.positionLabel) : that.positionLabel != null)
            return false;
        return salaryLabel != null ? salaryLabel.equals(that.salaryLabel) : that.salaryLabel == null;

    }

    @Override
    public int hashCode() {
        int result = nameLabel != null ? nameLabel.hashCode() : 0;
        result = 31 * result + (surnameLabel != null ? surnameLabel.hashCode() : 0);
        result = 31 * result + (birthdayLabel != null ? birthdayLabel.hashCode() : 0);
        result = 31 * result + (positionLabel != null ? positionLabel.hashCode() : 0);
        result = 31 * result + (salaryLabel != null ? salaryLabel.hashCode() : 0);
        return result;
    }
}
