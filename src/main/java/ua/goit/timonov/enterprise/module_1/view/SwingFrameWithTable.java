package ua.goit.timonov.enterprise.module_1.view;

import ua.goit.timonov.enterprise.module_1.controller.TestExecutor;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 27.05.2016.
 */
public class SwingFrameWithTable extends JFrame {
    public static final int SET_10K = 10000;
    public static final int SET_100K = 100000;
    public static final int SET_1000K = 1000000;
    public static final int ROWS = 5;
    public static final int COLUMNS = 8;

    private JTable table;

    public SwingFrameWithTable() throws HeadlessException {
        table = new JTable(ROWS, COLUMNS);
        String[] columnNames = {"Type", "populate", "add", "get", "delete", "contains", "iterator.add", "iterator.delete"};
        for (int j = 0; j < table.getColumnCount(); j++) {
            table.setValueAt(columnNames[j], 0, j);
        }
//        TableColumn tableColumn = new TableColumn();
        TableColumn column = table.getColumnModel().getColumn(COLUMNS - 1);
        column.setPreferredWidth(90);

        add(table, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        makeButton(buttonPanel, SET_10K);
        makeButton(buttonPanel, SET_100K);
        makeButton(buttonPanel, SET_1000K);

        JButton exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void makeButton(JPanel buttonPanel, int nElements) {
        JButton button = new JButton(String.valueOf(nElements));
        buttonPanel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestExecutor executor = new TestExecutor();
                executor.runTest(nElements);
                executor.printToFrameTable(table);
                executor.printTableToFile();
            }
        });
    }
}
