
package ua.goit.timonov.enterprise;

/**
 * Calc application
 *
 */
public class AppCalc {
    private InputOutput inputOutput;

    public static void main(String[] args) {
        AppCalc app = new AppCalc();
        app.execute();
    }

    public void execute() {
        inputOutput = new InputOutput();

        PermittedOperations permittedOperations = new AppPermittedOperations().getPermittedOperations();
        Calc calc = new CalcNumbers(permittedOperations);
        String input = inputOutput.inputStringFromConsole();
        String result = calc.doCalc(input);
        inputOutput.printToConsole(input, result);
    }
}
