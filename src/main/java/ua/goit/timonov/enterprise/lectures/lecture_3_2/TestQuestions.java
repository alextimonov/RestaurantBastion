package ua.goit.timonov.enterprise.lectures.lecture_3_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.stream.IntStream;

/**
 * Created by Alex on 14.06.2016.
 */
public class TestQuestions {

    public static class Test1 {
        public void test() {
            Exchanger<String> exchanger = new Exchanger<>();
            Random random = new Random();
            IntStream.range(0, 2).forEach((i) -> new Thread(() -> {
                try {
                    Thread.sleep(random.nextInt(1000));
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " ready to exchange");
                    System.out.println(name + " < - > " + exchanger.exchange(name));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
    }

    public static class Test2 {
        public void test() {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<String> f = executorService.submit((Callable<String>) () -> {
                throw new RuntimeException("Exception happened");
            });
            try {
                System.out.println("result: " + f.get());
            } catch (Exception e) { }
            executorService.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        new Test1().test();
        new Test2().test();


        /*Exchanger<String> exchanger = new Exchanger<>();
        Random random = new Random();
        IntStream.range(0, 2).forEach((i) -> new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(1000));
                String name = Thread.currentThread().getName();
                System.out.println(name + " ready to exchange");
                System.out.println(name + " < - > " + exchanger.exchange(name));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));*/
//        }).start());



        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> f = executorService.submit((Callable<String>) () -> {
            throw new RuntimeException("Exception happened");
        });
        try {
            System.out.println("result: " + f.get());
        } catch (Exception e) { }
        executorService.shutdown();*/



        /*List<Callable<String>> callables = new ArrayList<>();
        IntStream.range(0, 3).forEach(i -> callables.add(() -> String.valueOf(i)));

        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> result = executor.invokeAll(callables);

        for (Future f : result) {
            System.out.println(f.get());
        }
        executor.shutdown();*/



        /*ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        System.out.println("Task scheduled");
        executorService.scheduleAtFixedRate(() -> System.out.println("Task executed"), 1, 1, TimeUnit.SECONDS);*/




    }
}
