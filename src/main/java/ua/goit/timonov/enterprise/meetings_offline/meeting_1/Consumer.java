package ua.goit.timonov.enterprise.meetings_offline.meeting_1;

/**
 * Created by Alex on 08.06.2016.
 */
public class Consumer implements Runnable {
    private int consumedValue;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (!Main.producer.isProduced()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (Main.producer) {
                if (Main.producer.isProduced()) {
                    consumedValue = Main.producer.getProducedValue();
                    Main.producer.setProduced(false);
                    System.out.println("\t Consumed value: " + consumedValue);
                    Main.sleep(500);
                    Main.producer.notify();
                }
            }
        }
    }
}
