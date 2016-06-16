package ua.goit.timonov.enterprise.lectures.lecture_3_2;

import java.util.concurrent.*;

/**
 * Created by Alex on 15.06.2016.
 */
class MyThread implements Runnable {
    private String str;
    private Phaser p;

    MyThread(String str, Phaser p) {
        this.str = str;
        this.p = p;
        this.p.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        String tmp;
        System.out.println("Создание переменной");
        p.arriveAndAwaitAdvance();
        tmp = str;
        System.out.println("Присваивание переменной значение");
        p.arriveAndAwaitAdvance();
        System.out.println("tmp = " + tmp);
        p.arriveAndDeregister();
    }
}

public class PhaserTesting2 {
    public static void main(String[] args) {
        Phaser p = new Phaser(1);
        new MyThread("A", p);
        new MyThread("B", p);
        new MyThread("C", p);

        int num = p.getPhase();
        p.arriveAndAwaitAdvance();
        System.out.println("Конец фазы#" + num);
        num = p.getPhase();
        p.arriveAndAwaitAdvance();
        System.out.println("Конец фазы#" + num);
        num = p.getPhase();
        p.arriveAndAwaitAdvance();
        System.out.println("Конец фазы#" + num);
        p.arriveAndDeregister();
    }
}
