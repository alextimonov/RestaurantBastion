package ua.goit.timonov.enterprise.module_1;

import java.util.List;

/**
 * Created by Alex on 26.05.2016.
 */
public class TestFactory {

    public List<Test> getTestList(TestedOperation operation) {
        Test test = null;
        switch (operation) {
            case POPULATE: {
                test = new TestPopulate();
            }
            break;
            case ADD: {
                test = new TestAdd();
            }
            break;
            case GET: {
                test = new TestGet();
            }
            break;
            case REMOVE: {
                test = new TestRemove();
            }
            break;
            case CONTAINS: {
                test = new TestContains();
            }
            break;
            case ITERATOR_ADD: {
                test = new TestIteratorAdd();
            }
            break;
            case ITERATOR_REMOVE: {
                test = new TestIteratorRemove();
            }
            break;
        }
        return test;
    }
}
