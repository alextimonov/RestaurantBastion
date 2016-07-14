
package ua.goit.timonov.enterprise;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Calc application using CalcLib library
 * Main executable class
 *
 */
public class AppCalc {
    // input & output to console
    private InputOutput inputOutput;
    // object of calculator from CalcLib library
    private Calc calc;

    public AppCalc(InputOutput inputOutput, Calc calc) {
        this.inputOutput = inputOutput;
        this.calc = calc;
    }

    /**
     * starts application
     * @param args      arguments are not expected
     */
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml", "aop-context.xml");
        AppCalc appCalc = applicationContext.getBean("appCalc", AppCalc.class);
        appCalc.execute();
    }

    public void setInputOutput(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    public void setCalc(Calc calc) {
        this.calc = calc;
    }

    // makes simple user interface to calculate several expressions
    private void execute() {
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
