package ua.goit.timonov.enterprise.module_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Executor for Number values
 */
public class NumberExecutor implements Executor<Number> {
    /* equals to true if method execute() was invoked */
    private boolean executeWasInvoked = false;

    /* schedule of tasks to execute */
    private List<TaskWithValidator> taskSchedule = new ArrayList<>();

    /* list of valid results */
    private List<Number> validResults = new ArrayList<>();

    /* list of invalid results */
    private List<Number> invalidResults = new ArrayList<>();

    /**
     * adds task to schedule. The result of task is available with the method getValueResults()
     * throws an exception if method execute() has been already invoked
     * @param task      given task
     */
    @Override
    public void addTask(Task task) {
        if (executeWasInvoked) {
            throw new ExecuteWasInvokedException(task.toString() + " can't be added!");
        }
        taskSchedule.add(new TaskWithValidator(task, new NumberValidator()));
    }

    /**
     * adds task to schedule with result validator.
     * The result will be written to ValidResults if validator.isValid returns true for this result
     * The result will be written to InvalidResults if validator.isValid returns false for this result
     * throws an exception if method execute() has been already invoked
     * @param task              given task
     * @param validator         given validator
     */
    @Override
    public void addTask(Task task, Validator validator) {
        if (executeWasInvoked) {
            throw new ExecuteWasInvokedException(task.toString() + " can't be added!");
        }
        taskSchedule.add(new TaskWithValidator(task, validator));
    }

    /**
     * runs all tasks added to schedule
     */
    @Override
    public void execute() {
        executeWasInvoked = true;
        for (TaskWithValidator taskWithValidator : taskSchedule) {
            Task task = taskWithValidator.getTask();
            Validator validator = taskWithValidator.getValidator();
            executeTask(task, validator);
        }
    }

    // executes task and save its result to appropriate list with results
    private void executeTask(Task task, Validator validator) {
        try {
            task.execute();
            Number result = (Number) task.getResult();
            addResultToApropriateList(validator, result);
        }
        catch (TaskOverflowDataTypeException e) {
            Number result = (Number) task.getResult();
            invalidResults.add(result);
        }
    }

    // adds result to appropriate lits with results
    private void addResultToApropriateList(Validator validator, Number result) {
        if (validator.isValid(result)) {
            validResults.add(result);
        }
        else {
            invalidResults.add(result);
        }
    }

    /**
     * gets valid results, throws an exception if method execute() hasn't been invoked
     * @return      list of valid results
     */
    @Override
    public List getValidResults() {
        if (executeWasInvoked == false)
            throw new ExecuteWasNotInvokedException("Method execute() hasn't been invoked!");
        return validResults;
    }

    /**
     * gets invalid results, throws an exception if method execute() hasn't been invoked
     * @return      list of invalid results
     */
    @Override
    public List getInvalidResults() {
        if (executeWasInvoked == false)
            throw new ExecuteWasNotInvokedException("Method execute() hasn't been invoked!");
        return invalidResults;
    }
}
