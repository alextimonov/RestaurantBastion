package ua.goit.timonov.enterprise;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 17.07.2016.
 */
public class ExpressionProvider {

    private Map<String, ExpressionFactory> expressionFactoryMap = new HashMap<>();

    public ExpressionProvider() {
    }

    public void setExpressionFactoryMap(Map<String, ExpressionFactory> expressionFactoryMap) {
        this.expressionFactoryMap = expressionFactoryMap;
    }

    /**
     * @param expressionType
     * @return
     * @throws IllegalArgumentException
     */
    public Expression getExpression(String expressionType) {
        ExpressionFactory factory = expressionFactoryMap.get(expressionType);
        if (factory == null) {
            throw new IllegalArgumentException("Can not find such expressionType type: " + expressionType);
        }
        return factory.getExpression();
    }
}
