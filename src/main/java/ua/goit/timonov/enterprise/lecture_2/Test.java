package ua.goit.timonov.enterprise.lecture_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 30.05.2016.
 */
public class Test {

    public static void test() {
        List<Citizen> citizens = new ArrayList<>();
        Validator<Person> personValidator = new PersonValidator();
        List<Citizen> validPersons = filterInvalidValues(citizens, personValidator);
    }

    public  static <E> List<E> filterInvalidValues(List< E > elements, Validator<  ? super E > validator) {
        return elements;
    }
}

class Person {}

class Citizen extends Person {}

class Validator <T> {}

class PersonValidator extends Validator<Person> {}
