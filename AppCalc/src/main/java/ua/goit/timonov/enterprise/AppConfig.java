package ua.goit.timonov.enterprise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Alex on 27.06.2016.
 */

@Configuration
public class AppConfig {

    @Bean
    public AppCalc appCalc(PermittedOperations permittedOperations, FactoryExpression factoryExpression,
                           InputOutput inputOutput) {
        AppCalc appCalc = new AppCalc();
        appCalc.setInputOutput(inputOutput);
        appCalc.setPermittedOperations(permittedOperations);
        appCalc.setFactoryExpression(factoryExpression);
        appCalc.setCalc(calc());
        return appCalc;
    }

    @Bean
    public PermittedOperations permittedOperations() {
        PermittedOperations permittedOperations = new PermittedOperationsForApp();
        return permittedOperations;
    }

    @Bean
    public FactoryExpression factoryExpression() {
        FactoryExpression factoryExpression = new FactoryNumberExpressionForApp();
        return factoryExpression;
    }

    @Bean
    public InputOutput inputOutput() {
        InputOutput inputOutput = new InputOutput();
        return inputOutput;
    }

    @Bean
    public Calc calc() {
        Calc calc = new CalcNumbers(permittedOperations(), factoryExpression());
        return calc;
    }
}
