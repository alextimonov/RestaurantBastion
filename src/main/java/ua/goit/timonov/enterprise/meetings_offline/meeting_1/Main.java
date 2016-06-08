package ua.goit.timonov.enterprise.meetings_offline.meeting_1;

/**
 * Created by Alex on 08.06.2016.
 */
public class Main {
    static final Producer producer = new Producer();
    static final Consumer consumer = new Consumer();

    public static void main(String[] args) {
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
