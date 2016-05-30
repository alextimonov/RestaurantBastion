package ua.goit.timonov.enterprise.module_2;

/**
 * Created by Alex on 30.05.2016.
 */
public interface Task <T> {

    /**
     * runs task for execution
     */
    void execute();


    /**
     * return the result of execution
     * @return      result of execution
     */
     T getResult();

}
