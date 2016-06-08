package ua.goit.timonov.enterprise.lectures.lecture_2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alex on 31.05.2016.
 */
public class CheckPersonsTest {

    CheckPersons checkPersons = new CheckPersons();

    @Test
    public void testFilterInvalidValuesCheckPersons() {
        List<Person> persons = Arrays.asList(new Person("Pasha", "Pavlov"), new Person("Lena", "Levina"),
                new Person("Sasha", ""));
        Validator personValidator = new PersonValidator();
        boolean actual = checkPersons.isValidList(persons, personValidator);
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void testFilterInvalidValuesCheckCitizens() {
        List<Citizen> citizens = Arrays.asList(new Citizen("Pasha", "Pavlov", "Lvov"), new Citizen("Lena", "Levina", ""),
                new Citizen("Sasha", "", ""));
        Validator personValidator = new PersonValidator();
        boolean actual = checkPersons.isValidList(citizens, personValidator);
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void testFilterInvalidValuesFilterCitizens() {
        List<Citizen> citizens = Arrays.asList(new Citizen("Pasha", "Pavlov", "Lvov"), new Citizen("Lena", "Levina", ""),
                new Citizen("Sasha", "", ""));

        List<Citizen> filteredCitizens = checkPersons.filterInvalid(citizens, new PersonValidator());

        List<Citizen> expected = Arrays.asList(new Citizen("Pasha", "Pavlov", "Lvov"), new Citizen("Lena", "Levina", ""));
        assertEquals(filteredCitizens, expected);
    }
}