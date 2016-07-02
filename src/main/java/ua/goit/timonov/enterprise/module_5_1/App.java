package ua.goit.timonov.enterprise.module_5_1;

/**
 * Created by Alex on 01.07.2016.
 */
public class App {
    Expression expression;
    ExpressionFactory factory = new ExpressionFactory();

    void check() {

        expression = factory.makeExpression(1);
        expression.evaluate();
        System.out.println(expression.getResult().toString());

        expression = factory.makeExpression(2);
        expression.evaluate();
        System.out.println(expression.getResult().toString());

        expression = factory.makeExpression(3);
        expression.evaluate();
        System.out.println(expression.getResult().toString());

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
