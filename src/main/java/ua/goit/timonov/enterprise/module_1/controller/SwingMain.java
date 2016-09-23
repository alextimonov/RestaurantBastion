package ua.goit.timonov.enterprise.module_1.controller;

import ua.goit.timonov.enterprise.module_1.view.SwingFrameWithTable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Alex on 27.05.2016.
 */
public class SwingMain {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new SwingFrameWithTable();
                frame.setTitle("Collection testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
