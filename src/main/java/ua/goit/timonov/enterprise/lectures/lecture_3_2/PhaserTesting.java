package ua.goit.timonov.enterprise.lectures.lecture_3_2;

import java.util.concurrent.Phaser;

/**
 * Created by Alex on 15.06.2016.
 */
public class PhaserTesting {

    static int lines = 10;
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    static StringBuffer randomAlphabet = new StringBuffer();

    public static void main(String[] args) {

        final Phaser phaser = new Phaser() {
            protected boolean onAdvance(int phase, int parties) {
                // John Nash mode
                /* print "deviations"
				for (int i = 0; i < alphabet.length(); i++) {
					System.out.printf("%d ", randomAlphabet.indexOf(
							  alphabet.charAt(i) + "") - i);
				}
				System.out.println();
				*/
                System.out.println(randomAlphabet);
                //
                randomAlphabet = new StringBuffer();

                return phase >= lines; //loop count managing here
            }
        };

        // everyone have to wait for the main thread
        phaser.register();

        for (int i = 0; i < alphabet.length(); i++) {

            final char next = alphabet.charAt(i);
            phaser.register(); // ticket for the following thread

            new Thread() {
                public void run() {
                    do {
                        randomAlphabet.append(next);
                        phaser.arriveAndAwaitAdvance(); // checkout
                    } while (!phaser.isTerminated());
                }
            }.start();
        }

        System.out.println("Write this by hand and carry in the pocket:");
        // some additional preparations may be done here
        // release
        phaser.arriveAndDeregister();
    }
}
