package ua.goit.timonov.enterprise;

/**
 * calculator to evaluate an expression given with a string
 */
public interface Calc {


    /**
     * calculates given math expression and returns it's result
     * @param inputString               given String with math expression
     * @return                          String with result of calculated expression
     */
    String doCalc(String inputString);

}
