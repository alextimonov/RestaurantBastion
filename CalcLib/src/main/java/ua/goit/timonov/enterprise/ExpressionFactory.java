package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 18.07.2016.
 */

public abstract class ExpressionFactory<Argument, Result> {

    public abstract Expression<Argument, Result> getExpression();

}


