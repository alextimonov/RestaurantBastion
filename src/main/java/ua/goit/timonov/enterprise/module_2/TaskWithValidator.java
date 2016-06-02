package ua.goit.timonov.enterprise.module_2;

/**
 * Class for storage pair of task and its validator
 */
public class TaskWithValidator {
    /* task to execute */
    private Task task;
    /* validator for task's result */
    private Validator validator;

    public TaskWithValidator(Task task, Validator validator) {
        this.task = task;
        this.validator = validator;
    }

    public Task getTask() {
        return task;
    }

    public Validator getValidator() {
        return validator;
    }
}
