package ua.goit.timonov.enterprise.lectures.lecture_3_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by Alex on 14.06.2016.
 */
public class Conditions {

    public static void main(String[] args) {
        TransferQueue<String> transferQueue = new TransferQueue<>(10);
        IntStream.range(0, 5).forEach((i) -> new Thread(() -> {

            while (true) {
                try {
                    transferQueue.put(Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start());

        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Taking out " + transferQueue.take());
//                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public static class TransferQueue<T> {

        private final Lock lock = new ReentrantLock();
        private final Condition full = lock.newCondition();
        private final Condition empty = lock.newCondition();


        Object[] items;
        int putIndex = 0;
        int takeIndex = 0;
        int size = 0;

        public TransferQueue(int capacity) {
            items = new Object[capacity];
        }

        public void put(T value) throws InterruptedException {
            lock.lock();
            try {
                while (size == items.length) {
                    System.out.println("Queue is full! Thread " + Thread.currentThread().getName() + " starts waiting");
                    full.await();
                }
                items[putIndex] = value;
                if (++putIndex == items.length)
                    putIndex = 0;
                size++;
                empty.signal();
            } finally {
                lock.unlock();
            }
        }

        public T take() throws InterruptedException {
            lock.lock();
            try {
                while (size == 0) {
                    System.out.println("Queue is empty! Thread " + Thread.currentThread().getName() + " starts waiting");
                    empty.await();
                }
                T result = (T) items[takeIndex];
                if (++takeIndex == items.length)
                    takeIndex = 0;
                size--;
                full.signal();
                return result;
            } finally {
                lock.unlock();
            }
        }
    }
}
