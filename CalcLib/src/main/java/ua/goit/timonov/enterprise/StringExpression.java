package ua.goit.timonov.enterprise;

/**
 * Class to storage parsed expression with string representation of its arguments, operation and result
 */
public class StringExpression {
    private String operator;
    private String value1;
    private String value2;
    private String result;

    public StringExpression() {
    }

    public StringExpression(String operator, String value1, String value2) {
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
