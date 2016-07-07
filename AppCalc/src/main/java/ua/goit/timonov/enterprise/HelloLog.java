package ua.goit.timonov.enterprise;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Alex on 07.07.2016.
 */
public class HelloLog {
    static int value = 23;

    /* static logger */
//    private static Logger logger = LoggerFactory.getLogger(AppCalc.class);
//    public static final String PATH_TO_LOG_FILE = "resources/log/hello.log";

    public static void main(String[] args) {
//        BasicConfigurator.configure();
        Logger logger = LoggerFactory.getLogger(AppCalc.class);
        logger.info("Value is {}!", value);

//        try {
//            configureLogger();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // configures logger, redirects System.out & System.err to the log file
    private static void configureLogger() throws IOException {
//        Handler fileHandler = new FileHandler(PATH_TO_LOG_FILE, false);
//        fileHandler.setFormatter(new SimpleFormatter());
//        logger  .addHandler(fileHandler);
//
//        PrintStream printStream =
//                new PrintStream(
//                        new BufferedOutputStream(
//                                new FileOutputStream(PATH_TO_LOG_FILE, false)));
//        System.setErr(printStream);
//        System.setOut(printStream);
    }
}
