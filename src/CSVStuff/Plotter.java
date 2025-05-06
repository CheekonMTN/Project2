package CSVStuff;

import java.io.*;
import java.util.*;
import static java.lang.Math.sin;

public class Plotter {
    public static void main(String[] args) {
        // Generate sin points
        List<Point> sinGraph = new ArrayList<>();
        for (double x = -10; x <= 10; x += 0.01) {
            sinGraph.add(new Point(x, sin(x)));
        }
        exportCSV(sinGraph, "sin.csv");

        // Salt the data
        List<Point> saltedData = Salter.saltData(sinGraph, -0.05, 0.05);
        exportCSV(saltedData, "salted_sin.csv");

        // Smooth the data
        List<Point> smoothedData = Smoother.smoothData(saltedData, 25);
        exportCSV(smoothedData, "smoothed_sin.csv");
    }

    public static void exportCSV(List<? extends Point> data, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("x,y");
            for (Point p : data) {
                writer.println(p.toString());
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}