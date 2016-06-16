package ua.goit.timonov.enterprise.lectures.lecture_3_2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Created by Alex on 14.06.2016.
 */
public class ExecutorsExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorsExample example = new ExecutorsExample();
//        example.testExecute();
//        example.testSubmit();
//        example.testException();
//        example.testInvokeAny();
        example.testInvokeAll();
//        example.testScheduled();
//        example.testScheduleAtFixedRate();

    }

    public void testExecute() {
        Executor executor = Executors.newSingleThreadExecutor();
        System.out.println(Thread.currentThread().getName() + " submits task");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": Async task started");
//                this exception won't be caught
//                throw new RuntimeException("Exception");
            }
        });
    }

    public void testSubmit() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> f = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Task is executed";
            }
        });
        System.out.println("Waiting for result");
        System.out.println("result: " + f.get());
        executorService.shutdown();
    }

    public void testException() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> f = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                throw new RuntimeException("Exception happened");
            }
        });

        System.out.println("Waiting for result");
        Thread.sleep(1000);
        try {
            System.out.println("result: " + f.get());
        }
        catch (ExecutionException e) {
            System.out.println("Exception occurred and can be performed");
        }
        executorService.shutdown();
    }


    public void testInvokeAny() throws ExecutionException, InterruptedException {
        List<Callable<String>> callables = new ArrayList<>();
        Random random = new Random();
        IntStream.range(0, 3).forEach((i) -> callables.add(() -> {
            Thread.sleep(random.nextInt(1000));
            return String.valueOf(i);
        }));

        ExecutorService executor = Executors.newSingleThreadExecutor();
        String result = executor.invokeAny(callables);
        System.out.println(result);
        executor.shutdown();
    }

    public void testInvokeAll() throws ExecutionException, InterruptedException {
        List<Callable<String>> callables = new ArrayList<>();
        Random random = new Random();
        IntStream.range(0, 3).forEach((i) -> callables.add(() -> {
            Thread.sleep(random.nextInt(1000));
            return String.valueOf(i);
        }));

        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> result = executor.invokeAll(callables);

        for (Future<String> f : result) {
            System.out.println(f.get());
        }
        executor.shutdown();
    }

    public void testScheduled() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        System.out.println("Task scheduled " + new Date());
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task executed " + new Date());
            }
        }, 1, TimeUnit.SECONDS);
    }

    public void testScheduleAtFixedRate() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        System.out.println("Task scheduled " + new Date());
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task executed " + new Date());
            }
        }, 1, 1, TimeUnit.SECONDS);
        Thread.sleep(10_000);
        executor.shutdown();
    }
}
