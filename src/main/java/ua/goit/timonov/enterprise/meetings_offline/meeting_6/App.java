package ua.goit.timonov.enterprise.meetings_offline.meeting_6;

import javax.xml.ws.Endpoint;

/**
 * Created by Alex on 12.07.2016.
 */
public class App {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8081/myWs", new ProcessorImpl());

    }
}
