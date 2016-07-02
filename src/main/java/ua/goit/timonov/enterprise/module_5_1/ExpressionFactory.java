package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 01.07.2016.
 */
public class ExpressionFactory {

    public Expression makeExpression(int k) {
        if (k == 1) {
            return new ExpressionIntegerPlusMinus(3, 2, new OperationIntegerPlus());
        }
        else {
            if (k == 2) {
                return new ExpressionIntegerPlusMinus(3, 2, new OperationIntegerMinus());
            }
            else {
                return new ExpressionIntegerDivide(3, 2, new OperationIntegerDivide());
            }
        }
    }
}

