package org.spring2023.io;

import org.spring2023.util.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadCSV {

    public ArrayList<Node> getPointsFromCSV(String path) {
        ArrayList<Node> nodes = new ArrayList<>();
        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            reader.readLine();


            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                String crimeId = values[0];
                double longitude = Double.parseDouble(values[1]);
                double latitude = Double.parseDouble(values[2]);


                Node node = new Node(crimeId, longitude, latitude);
                nodes.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodes;

    }
}
