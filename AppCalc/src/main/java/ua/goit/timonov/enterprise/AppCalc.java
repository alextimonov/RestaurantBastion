
package ua.goit.timonov.enterprise;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Calc application
 *
 */
public class AppCalc {
    private InputOutput inputOutput;
    private PermittedOperations permittedOperations;
    private FactoryExpression factoryExpression;
    private Calc calc;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AppCalc appCalc = applicationContext.getBean("appCalc", AppCalc.class);
        appCalc.execute();
    }

    public void setInputOutput(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    public void setPermittedOperations(PermittedOperations permittedOperations) {
        this.permittedOperations = permittedOperations;
    }

    public void setFactoryExpression(FactoryExpression factoryExpression) {
        this.factoryExpression = factoryExpression;
    }

    public void setCalc(Calc calc) {
        this.calc = calc;
    }

    public void execute() {

//        inputOutput = new InputOutput();
//        permittedOperations = new PermittedOperationsForApp();
//        factoryExpression = new FactoryNumberExpressionForApp();
//        Calc calc = new CalcNumbers(permittedOperations, factoryExpression);

        while (true) {
            String input = inputOutput.inputStringFromConsole();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
            try {
                String result = calc.doCalc(input);
                inputOutput.printToConsole(input, result);
            }
            catch (RuntimeException e) {
                inputOutput.printErrorToConsole(input, e.getMessage());
            }
        }
    }
}
