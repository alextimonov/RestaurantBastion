package ua.goit.timonov.enterprise;

/**
 * Created by Alex on 30.06.2016.
 */
public interface StringParser {

    StringExpression parse(String inputString, PermittedOperations permittedOperations);

}
