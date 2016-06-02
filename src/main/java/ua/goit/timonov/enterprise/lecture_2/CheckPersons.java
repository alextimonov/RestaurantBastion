package ua.goit.timonov.enterprise.lecture_2;

//import org.apache.commons.lang3;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 30.05.2016.
 */
public class CheckPersons {

    public static void test() {
        List<Citizen> citizens = new ArrayList<>();
        Validator<Person> personValidator = new PersonValidator();
        List<Citizen> validPersons = filterInvalidValues(citizens, personValidator);
    }

    public  static <E> List<E> filterInvalidValues(List< E > elements, Validator<  ? super E > validator) {
        return elements;
    }

    public boolean isValidList(List<? extends Person> persons, Validator<Person> personValidator) {
        for (Person person : persons) {
            if (!personValidator.isValid(person)) {
                return false;
            }
        }
        return true;
    }

    public <T> List<T> filterInvalid(List<T> values, Validator<? super T> validator) {
        List <T>  result = new ArrayList<>();
        for (T value : values) {
            if (validator.isValid(value)) {
                result.add(value);
            }
        }
        return result;
    }
}

class Person {
    public String name;
    public String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

class Citizen extends Person {
    public String address;

    public Citizen(String name, String surname, String address) {
        super(name, surname);
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Citizen{");
        sb.append("name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

interface Validator <T> {
    boolean isValid(T value);
}

class PersonValidator implements Validator<Person> {

    @Override
    public boolean isValid(Person value) {
        return value.name != "" && value.surname != "";
    }
}