**Домашнее задание к Модулю 2**
---------------------
**Написать фреймворк для последоватьного выполнения задач:**

1 Переписать интерфейс Task так что бы он был типизирован по результату (значению возращаемуому методом getResult()).

2 Переписать интерфейс Validator так что бы он был типизирован по принемаемому значению isValid(Object result);

3 Переписать интерфейс Executor так чтоб он был типизирован в соответсвии с с типизацией Task и Validator

4 Импелементирвать интерфейс Executor

5 Написать к нему тесты.

	public interface Executor {

        // Добавить таск на выполнение. Результат таска будет доступен через метод getValidResults(). 
		// Бросает Эксепшн если уже был вызван метод execute()
        void addTask(Task task);

        // Добавить таск на выполнение и валидатор результата. Результат таска будет записан в ValidResults если validator.isValid вернет true для этого результата
		// Результат таска будет записан в InvalidResults если validator.isValid вернет false для этого результата
		// Бросает Эксепшн если уже был вызван метод execute()
        void addTask(Task task, Validator validator);

        // Выполнить все добавленые таски
        void execute();

        // Получить валидные результаты. Бросает Эксепшн если не был вызван метод execute()
        List getValidResults();

        // Получить невалидные результаты. Бросает Эксепшн если не был вызван метод execute()
        List getInvalidResults();

    }

    public interface Task {

        // Метода запускает таск на выполнение
        void execute();

        // Возвращает результат выполнения
        Object getResult();


    }

    public interface Validator {

        // Валидирует переданое значение
        boolean isValid(Object result);

    }
Пример использования

	public void test(List<Task<Integer>> intTasks) {
        Executor<Number> numberExecutor = new ExecutorImpl<>();

        for (Task<Integer> intTask : intTasks) {
            numberExecutor.addTask(intTask);
        }
        numberExecutor.addTask(new LongTask(10L), new NumberValidator());
		
        numberExecutor.execute();
        
        System.out.println("Valid results:");
        for (Number number : numberExecutor.getValidResults()) {
            System.out.println(number);
        }
        System.out.println("Invalid results:");
        for (Number number : numberExecutor.getInvalidResults()) {
            System.out.println(number);
        }
    }
где ExecutorImpl некая импелментация Executor

LongTask некая иплементация Task типизированя по Long

NumberValidator некая имплеиентация Validator типизированя по Number