package ua.goit.timonov.enterprise;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex on 01.07.2016.
 */
public class PermittedOperationsTest {
    PermittedOperations permittedOperations = new PermittedOperations();

    @Test
    public void testGetSetOperations() throws Exception {
        System.out.println(permittedOperations.getSetOperations());
    }
}