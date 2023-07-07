package org.spring2023.io;

import org.spring2023.util.Node;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class ReadCSVUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;

    public ReadCSVUI() {
        setTitle("CSV Reader");

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);

        JButton btnLoad = new JButton("Load CSV");
        panel.add(btnLoad);
        btnLoad.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*.csv)", "csv");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getPath();
                ReadCSV reader = new ReadCSV();

                //todo: Use the nodes
                List<Node> nodes = reader.getPointsFromCSV(path);


                // Initialize model if not already done
                if (model == null) {
                    model = new DefaultTableModel();
                    table.setModel(model);
                    model.addColumn("Crime ID");
                    model.addColumn("Longitude");
                    model.addColumn("Latitude");
                }
                model.setRowCount(0);
                for (Node node : nodes) {
                    model.addRow(new Object[]{node.getName(), node.getX(), node.getY()});
                }
            }
        });

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);


        //todo: Temporary code generate random number.
        JButton btnRandom = new JButton("Generate Random Number");
        getContentPane().add(btnRandom, BorderLayout.SOUTH);
        btnRandom.addActionListener(e -> {
            Random rand = new Random();
            int randomNum = rand.nextInt(100);
            JOptionPane.showMessageDialog(this, "Random number: " + randomNum);
        });

        setPreferredSize(new Dimension(500, 400));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
