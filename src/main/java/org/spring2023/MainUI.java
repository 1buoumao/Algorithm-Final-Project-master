package org.spring2023;

import org.spring2023.algo.ChristofidesTSP;
import org.spring2023.algo.optimization.strategic.TSPGeneticAlgorithm;
import org.spring2023.algo.optimization.strategic.TSPSimulatedAnnealing;
import org.spring2023.algo.optimization.tactical.RandomSwap;
import org.spring2023.algo.optimization.tactical.TwoOpt;
import org.spring2023.display.TSPVisualizer;
import org.spring2023.io.ReadCSV;
import org.spring2023.util.ComputeCycleCost;
import org.spring2023.util.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainUI extends JFrame implements ActionListener {
    private JButton oneButton;
    private JButton twoButton;
    private JButton randomSwapButton;

    private JButton simAnnealButton;

    private JButton geneticButton;

    public MainUI() {
        super("TSP Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the buttons
        oneButton = new JButton("Christofides Algorithm");
        twoButton = new JButton("2 Opt Optimization");
        randomSwapButton = new JButton("Random Swap Optimization");
        simAnnealButton = new JButton("Simulated Annealing Optimization");
        geneticButton = new JButton("Genetic Algorithm Optimization");

        // add the buttons to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(oneButton);
        contentPane.add(randomSwapButton);
        contentPane.add(twoButton);
        contentPane.add(simAnnealButton);
        contentPane.add(geneticButton);

        // add action listeners to the buttons
        oneButton.addActionListener(this);
        randomSwapButton.addActionListener(this);
        twoButton.addActionListener(this);
        simAnnealButton.addActionListener(this);
        geneticButton.addActionListener(this);

        // set the size and make the frame visible
        setSize(300, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // read the nodes from a CSV file
        ReadCSV reader = new ReadCSV();
        ArrayList<Node> nodes = reader.getPointsFromCSV("src/main/resources/info6205.spring2023.teamproject.csv");

        // compute the tour using the Christofides TSP algorithm
        ChristofidesTSP christofidesTSP = new ChristofidesTSP();
        List<Node> tour = christofidesTSP.run(nodes);

        // create the TSPVisualizer object(s) based on the button that was clicked
        if (e.getSource() == oneButton) {
            TSPVisualizer nodeGraph = new TSPVisualizer(tour);
            displayTSPVisualizer(nodeGraph, tour);
        } else if (e.getSource() == twoButton) {
            // get the max iterations from user input
            int maxIterations = Integer.parseInt(JOptionPane.showInputDialog("Enter max iterations:"));

            // optimize the tour using the 2-opt algorithm
            List<Node> tour2Opt = TwoOpt.towOptOptimizationReturnTore(tour, maxIterations);
            TSPVisualizer nodeGraph1 = new TSPVisualizer(tour);
            TSPVisualizer nodeGraph2 = new TSPVisualizer(tour2Opt);

            displayTwoTSPVisualizers(nodeGraph1, nodeGraph2, tour, tour2Opt, maxIterations);
        } else if (e.getSource() == randomSwapButton) {
            // get the max iterations from user input
            int maxIterations = Integer.parseInt(JOptionPane.showInputDialog("Enter max iterations:"));

            // optimize the tour using the random swap algorithm
            List<Node> tourRandomSwap = RandomSwap.randomSwapOptimizationReturnTour(tour, maxIterations);
            TSPVisualizer nodeGraph1 = new TSPVisualizer(tour);
            TSPVisualizer nodeGraph2 = new TSPVisualizer(tourRandomSwap);

            displayRandomSwapTSPVisualizers(nodeGraph1, nodeGraph2, tour, tourRandomSwap, maxIterations);

        } else if (e.getSource() == simAnnealButton) {
            // get the max iterations and cooling rate from user input
            double coolingRate = Double.parseDouble(JOptionPane.showInputDialog("Enter cooling rate (should be less than 1, e.g. 0.995):"));

            // optimize the tour using the simulated annealing algorithm
            TSPSimulatedAnnealing sa = new TSPSimulatedAnnealing(tour, 1000000, coolingRate);
            List<Node> optimizedTour = sa.run();
            TSPVisualizer nodeGraph1 = new TSPVisualizer(tour);
            TSPVisualizer nodeGraph2 = new TSPVisualizer(optimizedTour);

            displaySimulatedAnnealingTSPVisualizers(nodeGraph1, nodeGraph2, tour, optimizedTour, coolingRate);
        } else if (e.getSource() == geneticButton) {
            System.out.println(ComputeCycleCost.getTotalDistance(tour));
            // get the number of generations from user input
            int numGenerations = Integer.parseInt(JOptionPane.showInputDialog("Enter number of generations:"));

            // get the population size from user input
            int populationSize = Integer.parseInt(JOptionPane.showInputDialog("Enter population size:"));

            // get the mutation rate from user input
            double mutationRate = Double.parseDouble(JOptionPane.showInputDialog("Enter mutation rate:"));

            // optimize the tour using the genetic algorithm

            List<Node> tempTour = new ArrayList<>();
            for (Node n: tour) {
                tempTour.add(new Node(n.getName(), n.getX(), n.getY()));
            }

            TSPGeneticAlgorithm ga = new TSPGeneticAlgorithm(tempTour, populationSize, mutationRate);
            List<Node> tourGenetic = (ga.run(numGenerations)).nodes;
            TSPVisualizer nodeGraph1 = new TSPVisualizer(tour);
            TSPVisualizer nodeGraph2 = new TSPVisualizer(tourGenetic);
            System.out.println(ComputeCycleCost.getTotalDistance(tour));
            displayGeneticTSPVisualizers(nodeGraph1, nodeGraph2, tour, tourGenetic, numGenerations, populationSize, mutationRate);

        }
    }

    private void displayTSPVisualizer(TSPVisualizer nodeGraph, List<Node> tour) {
        // create a new frame to display the TSPVisualizer
        JFrame frame = new JFrame("Christofides Algorithm Visualizer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(nodeGraph);


        JLabel label = new JLabel("Total distance of our Christofides Algorithm is " + ComputeCycleCost.getTotalDistance(tour) + " meters", SwingConstants.CENTER);
        frame.add(label, BorderLayout.SOUTH);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void displayTwoTSPVisualizers(TSPVisualizer nodeGraph1, TSPVisualizer nodeGraph2, List<Node> tour, List<Node> tour2Opt, int maxIterations) {
        // create a new frame to display the two TSPVisualizer objects
        JFrame frame = new JFrame("Christofides and Two Opt Optimization TSP Visualizers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create a panel with a GridLayout of one row and two columns
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(nodeGraph1);
        panel.add(nodeGraph2);

        // add the panel to the frame and make the frame visible
        frame.add(panel);





        // display the max iterations input by the user as a string in the top left corner of each TSPVisualizer
        Font font = new Font("Arial", Font.BOLD, 14);
        JLabel label1 = new JLabel("Christofides" );
        label1.setFont(font);
        label1.setForeground(Color.BLACK);
        label1.setBackground(Color.WHITE);
        label1.setOpaque(true);
        nodeGraph1.add(label1);

        JLabel label2 = new JLabel("2 Opt optimized when max iterations is " + maxIterations);
        label2.setFont(font);
        label2.setForeground(Color.BLACK);
        label2.setBackground(Color.WHITE);
        label2.setOpaque(true);
        nodeGraph2.add(label2);

//        JLabel labelBig

        double costOfC = ComputeCycleCost.getTotalDistance(tour);
        double costOfTwoOpt = ComputeCycleCost.getTotalDistance(tour2Opt);

        JLabel label = new JLabel("<html>The total distance of Christofides is " + costOfC +
                "<br/>When max iterations is " + maxIterations +
                "<br/>The total distance after 2 opt optimization is " + costOfTwoOpt + "<br/>" +
                ComputeCycleCost.getOptimizationRate(costOfC, costOfTwoOpt) + "</html>", SwingConstants.CENTER);
        //label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.SOUTH);


        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void displayRandomSwapTSPVisualizers(TSPVisualizer nodeGraph1, TSPVisualizer nodeGraph2, List<Node> tour, List<Node> tourRandomSwap, int maxIterations) {
        // create a new frame to display the two TSPVisualizer objects
        JFrame frame = new JFrame("Christofides and Random Swap Optimization TSP Visualizers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create a panel with a GridLayout of one row and two columns
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(nodeGraph1);
        panel.add(nodeGraph2);

        // add the panel to the frame and make the frame visible
        frame.add(panel);





        // display the max iterations input by the user as a string in the top left corner of each TSPVisualizer
        Font font = new Font("Arial", Font.BOLD, 14);
        JLabel label1 = new JLabel("Christofides" );
        label1.setFont(font);
        label1.setForeground(Color.BLACK);
        label1.setBackground(Color.WHITE);
        label1.setOpaque(true);
        nodeGraph1.add(label1);

        JLabel label2 = new JLabel("Random Swap optimized");
        label2.setFont(font);
        label2.setForeground(Color.BLACK);
        label2.setBackground(Color.WHITE);
        label2.setOpaque(true);
        nodeGraph2.add(label2);

//        JLabel labelBig

        double costOfC = ComputeCycleCost.getTotalDistance(tour);
        double costOfTwoOpt = ComputeCycleCost.getTotalDistance(tourRandomSwap);

        JLabel label = new JLabel("<html>The total distance of Christofides is " + costOfC +
                "<br/>When max iterations is " + maxIterations +
                "<br/>The total distance after random swap optimization is " + costOfTwoOpt + "<br/>" +
                ComputeCycleCost.getOptimizationRate(costOfC, costOfTwoOpt) + "</html>", SwingConstants.CENTER);
        //label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.SOUTH);


        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void displaySimulatedAnnealingTSPVisualizers(TSPVisualizer nodeGraph1, TSPVisualizer nodeGraph2, List<Node> tour, List<Node> tourSimulatedAnnealing, double coolingRate) {
        // create a new frame to display the two TSPVisualizer objects
        JFrame frame = new JFrame("Christofides and Simulated Annealing Optimization TSP Visualizers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create a panel with a GridLayout of one row and two columns
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(nodeGraph1);
        panel.add(nodeGraph2);

        // add the panel to the frame and make the frame visible
        frame.add(panel);

        // display the max iterations and cooling rate input by the user as a string in the top left corner of each TSPVisualizer
        Font font = new Font("Arial", Font.BOLD, 14);
        JLabel label1 = new JLabel("Christofides" );
        label1.setFont(font);
        label1.setForeground(Color.BLACK);
        label1.setBackground(Color.WHITE);
        label1.setOpaque(true);
        nodeGraph1.add(label1);

        JLabel label2 = new JLabel("Simulated Annealing optimized");
        label2.setFont(font);
        label2.setForeground(Color.BLACK);
        label2.setBackground(Color.WHITE);
        label2.setOpaque(true);
        nodeGraph2.add(label2);

        double costOfC = ComputeCycleCost.getTotalDistance(tour);
        double costOfSimulatedAnnealing = ComputeCycleCost.getTotalDistance(tourSimulatedAnnealing);

        JLabel label = new JLabel("<html>The total distance of Christofides is " + costOfC +
                "<br/>The total distance after simulated annealing optimization with initial temperature is 1000000, cooling rate " + coolingRate + " is " + costOfSimulatedAnnealing + "<br/>" +
                ComputeCycleCost.getOptimizationRate(costOfC, costOfSimulatedAnnealing) + "</html>", SwingConstants.CENTER);
        panel.add(label, BorderLayout.SOUTH);

        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void displayGeneticTSPVisualizers(TSPVisualizer nodeGraph1, TSPVisualizer nodeGraph2, List<Node> tour, List<Node> tourGenetic, int numGenerations, int populationSize, double mutationRate) {
        // create a new frame to display the two TSPVisualizer objects
        JFrame frame = new JFrame("Christofides and Genetic Algorithm TSP Visualizers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create a panel with a GridLayout of one row and two columns
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(nodeGraph1);
        panel.add(nodeGraph2);

        // add the panel to the frame and make the frame visible
        frame.add(panel);

        // display the parameters used for the genetic algorithm
        Font font = new Font("Arial", Font.BOLD, 14);
        JLabel label1 = new JLabel("Christofides" );
        label1.setFont(font);
        label1.setForeground(Color.BLACK);
        label1.setBackground(Color.WHITE);
        label1.setOpaque(true);
        nodeGraph1.add(label1);

        JLabel label2 = new JLabel("Genetic Algorithm optimized");
        label2.setFont(font);
        label2.setForeground(Color.BLACK);
        label2.setBackground(Color.WHITE);
        label2.setOpaque(true);
        nodeGraph2.add(label2);

        // display the tour distances for both Christofides and Genetic Algorithm
        double costOfC = ComputeCycleCost.getTotalDistance(tour);
        System.out.println(costOfC);
        double costOfGenetic = ComputeCycleCost.getTotalDistance(tourGenetic);
        JLabel label = new JLabel("<html>The total distance of Christofides is " + costOfC +
                "<br/>When the number of generations is " + numGenerations + " the population size is " + populationSize +
                " the mutation rate is " + mutationRate
                + " The total distance after Genetic Algorithm optimization is " + costOfGenetic + "<br/>" +
                ComputeCycleCost.getOptimizationRate(costOfC, costOfGenetic) + "</html>", SwingConstants.CENTER);
        panel.add(label, BorderLayout.SOUTH);

        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
