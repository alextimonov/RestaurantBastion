package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 01.07.2016.
 */
public class App {
    Operation operation = new OperationInteger();
    Operation<Integer> operationInt;
    OperationInteger operationInteger;

    ExpressIface expression;


    ExpressionInteger expressionInteger = new ExpressionInteger();
    ExpressionFactory factory = new ExpressionFactory();

    void check() {

        expression = factory.makeExpression();
        expression.evaluate();
        expression.getResult();

        int q = 5;
//        Integer v1 = expression.getX1();




//        expressionInteger = factory.makeExpression();

//        expressionInteger.getOperation().calc(3, 2);
//        operation = expressionInteger.getOperation();
//        operation.calc();
//        int x1 = expressionInteger.getX1();
//        int x2 = expressionInteger.getX2();
//        int k = operation.calc(3, 2);

//        int res = operation.calc(expressionInteger.getX1(), expressionInteger.getX2());

    }

    public static void main(String[] args) {
        new App().check();
    }
}
