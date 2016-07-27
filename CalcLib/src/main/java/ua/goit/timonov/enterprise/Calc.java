package ua.goit.timonov.enterprise;

/**
 * calculator to evaluate an expressionType given with a string
 */
public interface Calc {


    /**
     * calculates given math expressionType and returns it's result
     * @param inputString               given String with math expressionType
     * @return                          String with result of calculated expressionType
     */
    String doCalc(String inputString);

}
