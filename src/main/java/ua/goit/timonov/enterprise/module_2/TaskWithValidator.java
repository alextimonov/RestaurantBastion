package ua.goit.timonov.enterprise.module_2;

/**
 * Class for storage pair of task and its validator
 */
public class TaskWithValidator<T> {
    /* task to execute */
    private Task<T> task;
    /* validator for task's result */
    private Validator<T> validator;

    public TaskWithValidator(Task<T> task, Validator<T> validator) {
        this.task = task;
        this.validator = validator;
    }

    public Task<T> getTask() {
        return task;
    }

    public Validator<T> getValidator() {
        return validator;
    }
}
