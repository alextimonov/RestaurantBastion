package ua.goit.timonov.enterprise.module_2;

/**
 * Created by Alex on 30.05.2016.
 */
public class TaskWithValidator<T> {
    private Task task;
    private Validator validator;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
