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

    public List<TaskWithValidator> getTaskSchedule() {
        return taskSchedule;
    }

    /**
     * adds task to schedule. The result of task is available with the method getValueResults()
     * @param task                           given task
     * @throws ExecuteWasInvokedException    generated if method execute() has been already invoked
     */
    @Override
    public void addTask(Task<? extends Number> task) {
        if (executeWasInvoked) {
            throw new ExecuteWasInvokedException(task.toString() + " can't be added!");
        }
        taskSchedule.add(new TaskWithValidator(task, new NumberValidator()));
    }

    /**
     * adds task to schedule with result validator.
     * The result will be written to ValidResults if validator.isValid returns true for this result
     * The result will be written to InvalidResults if validator.isValid returns false for this result
     * @param task                              given task
     * @param validator                         given validator
     * @throws ExecuteWasInvokedException       generated if method execute() has been already invoked
     */
    @Override
    public void addTask(Task<? extends Number> task, Validator<? super Number> validator) throws ExecuteWasInvokedException {
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
    private void executeTask(Task<? extends Number> task, Validator<Number> validator) {
        try {
            task.execute();
            Number result = task.getResult();
            addResultToAppropriateList(validator, result);
        }
        catch (TaskOverflowDataTypeException e) {
            Number result = task.getResult();
            invalidResults.add(result);
        }
    }

    // adds result to appropriate lits with results
    private void addResultToAppropriateList(Validator<Number>  validator, Number result) {
        if (validator.isValid(result)) {
            validResults.add(result);
        }
        else {
            invalidResults.add(result);
        }
    }

    /**
     * gets valid results, throws an exception if method execute() hasn't been invoked
     * @return                                  list of valid results
     * @throws ExecuteWasNotInvokedException    is generated if method execute() hasn't been invoked
     */
    @Override
    public List<Number> getValidResults() {
        if (!executeWasInvoked)
            throw new ExecuteWasNotInvokedException("Method execute() hasn't been invoked!");
        return validResults;
    }

    /**
     * gets invalid results, throws an exception if method execute() hasn't been invoked
     * @return                                  list of invalid results
     * @throws ExecuteWasNotInvokedException    is generated if method execute() hasn't been invoked
     */
    @Override
    public List<Number> getInvalidResults() {
        if (!executeWasInvoked)
            throw new ExecuteWasNotInvokedException("Method execute() hasn't been invoked!");
        return invalidResults;
    }
}
