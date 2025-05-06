package CSVStuff;

import java.io.*;
import java.util.*;

public class DataHandler {


    public static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }

    // reads x,y data from a CSV file and loads it into a list
    // Update loadCSV in DataHandler.java
    public static List<String> loadCSV(String filename) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) { skipHeader = false; continue; }
                data.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Couldn't read file: " + e.getMessage());
            return null;
        }
        return data;
    }

    // dumps a list of points into a CSV file
    public static void exportCSV(List<Point> data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X,Y\n");
            for (Point p : data) {
                writer.write(p.toString() + "\n");
            }
            System.out.println("Exported to: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
