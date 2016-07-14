package ua.goit.timonov.enterprise.meetings_offline.meeting_6;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Alex on 12.07.2016.
 */
public class App1 {

    public static void main(String[] args) {

        String json = "{\"key\": key ,\"value\": key ";

        JSONArray array = new JSONArray();
        for (int i = 0; i < 5; i++) {
            JSONObject root = new JSONObject();
            root.put("name", "Piter");
            root.put("age", "23");
            array.put(i, root);
        }

        JSONObject root = new JSONObject();
        root.put("name", "Simon");
        root.put("age", "31");
        System.out.println(root);
        System.out.println("------------------");

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            System.out.println(object.get("name"));
            System.out.println(object.get("age"));
        }

    }
}
