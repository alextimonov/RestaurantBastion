package ua.goit.timonov.enterprise.module_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 30.05.2016.
 */
public class ExecutorImpl<T> implements Executor {
    private boolean executeWasInvoked = false;

    private List<Task> tasks = new ArrayList<>();
    private List<TaskWithValidator> tasksAndResult = new ArrayList<>();
    private List<T> validResults = new ArrayList<>();
    private List<T> invalidResults = new ArrayList<>();

    @Override
    public void addTask(Task task) {
        if (executeWasInvoked) {
            throw new ExecuteWasInvokedException(task.toString() + " can't be added!");
        }
        tasks.add(task);
    }

    @Override
    public void addTask(Task task, Validator validator) {
        if (executeWasInvoked) {
            throw new ExecuteWasInvokedException(task.toString() + " can't be added!");
        }
        tasks.add(task);
    }

    @Override
    public void execute() {
        executeWasInvoked = true;
        for (Task task : tasks) {
            task.execute();
            T result = (T) task.getResult();
            Validator validator = null;
            if (validator.isValid(result)) {
                validResults.add(result);
            }
            else {
                invalidResults.add(result);
            }
        }
    }

    @Override
    public List getValidResults() {
        return validResults;
    }

    @Override
    public List getInvalidResults() {
        return invalidResults;
    }
}
