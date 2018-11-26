package com.completionCheck.project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> files = new ArrayList<>();
        files.add("test.java");
        files.add("test2.java");
        files.add("test3.java");
        files.add("test4.java");
        files.add("test5.java");
        files.add("test6.java");
        files.add("test7.java");
        files.add("test8.java");
        files.add("test9.java");
        files.add("test10.java");
        files.add("test11.java");
        files.add("test12.java");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui(StatusCheck.checkFiles(files));
            }
        });
    }

    private static void createAndShowGui(List<Integer> scores) {
        GraphPanel mainPanel = new GraphPanel(scores);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Number of TODOs and Prints");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JFrame f = new JFrame("Code Pictogram");
        Component c = new PictogramPanel();
        f.getContentPane().add(c);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

        // TODO: scroll size not dynamic, will cut off if file is too long
        JScrollPane scroller = new JScrollPane(c);
        f.getContentPane().add(scroller, BorderLayout.CENTER);


    }
}
