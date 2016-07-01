package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 01.07.2016.
 */
public class ExpressionFactory {

    public ExpressIface makeExpression() {
        if (true) {
            return new ExpressionInteger(3, 2, new OperationInteger());
        } else
            return new ExpressionLong();
    }
}
