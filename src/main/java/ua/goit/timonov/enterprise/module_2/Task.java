package ua.goit.timonov.enterprise.module_2;

/**
 * Some task that can be executed
 */
public interface Task <T> {

    /**
     * executes the task
     */
    void execute();


    /**
     * return the result of execution
     * @return      result of execution
     */
     T getResult();

}
