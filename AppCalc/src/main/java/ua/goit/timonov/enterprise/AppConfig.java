package ua.goit.timonov.enterprise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * Java-based Spring configuration class for AppCalc
 */

@Configuration
@EnableAspectJAutoProxy
@Import(LogAspect.class)
public class AppConfig {

    @Bean
    public AppCalc appCalc() {
        AppCalc appCalc = new AppCalc();
        appCalc.setInputOutput(inputOutput());
        appCalc.setCalc(calc());
        return appCalc;
    }

    @Bean
    public StringParser stringParser() {
        StringParser stringParser = new ParserStringToStringExpression();
        return stringParser;
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
        Calc calc = new CalcNumbers(permittedOperations(), factoryExpression(), stringParser());
        return calc;
    }

    @Bean
    public CalcNumbers calcNumbers() {
        CalcNumbers calcNumbers = new CalcNumbers(permittedOperations(), factoryExpression(), stringParser());
        return calcNumbers;
    }
}
