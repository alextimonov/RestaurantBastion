package ua.goit.timonov.enterprise.module_2;

import java.util.List;

/**
 * Executor for objects implementing interfaces Task and Validator
 */
public interface Executor<T> {

    /**
     * adds task to execution. The result of task is available with the method getValueResults()
     * throws an exception if method execute() has been already invoked
     * @param task      given task
     */
    void addTask(Task<? extends T> task);

    /**
     * adds task to execution with result validator.
     * The result will be written to ValidResults if validator.isValid returns true for this result
     * The result will be written to InvalidResults if validator.isValid returns false for this result
     * throws an exception if method execute() has been already invoked
     * @param task              given task
     * @param validator         given validator
     */
    void addTask(Task<? extends T> task, Validator<T> validator);

    /**
     * runs all added tasks
     */
    void execute();

    /**
     * gets valid results, throws an exception if method execute() hasn't been invoked
     * @return      list of valid results
     */
    List<T> getValidResults();

    /**
     * gets invalid results, throws an exception if method execute() hasn't been invoked
     * @return      list of invalid results
     */
    List<T> getInvalidResults();
}
