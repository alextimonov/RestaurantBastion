package ua.goit.timonov.enterprise.meetings_offline.meeting_0;

import java.util.Arrays;

/**
 * Created by Alex on 04.06.2016.
 */
public class Main {
    public static void main(String[] args) {
        SimpleList<Integer> list = new SimpleListGeneric<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        list.addAll(Arrays.asList(4, 5, 6));
        System.out.println(list);
        System.out.println(list.get(3));
        Integer n = list.set(2, 10);
        System.out.println(n);
        System.out.println(list);
        System.out.println(list.remove(6));
        System.out.println(list.size());
        System.out.println(list.contains(10));
        System.out.println(list.isEmpty());
    }
}
