package CSVStuff;
import java.io.*;
import java.util.*;

public class Smoother {
    public static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }

    public static void main(String[] args) {
        String filename = "salted_output.csv"; // using the salted file
        int windowValue = 5; // looking 5 points left and right

        // load the noisy data from the file
        List<Point> originalData = loadCSV(filename);
        if (originalData == null) return;

        System.out.println("Original Data:");
        originalData.forEach(System.out::println);

        // do the smoothing — average y values around each point
        List<Point> smoothedData = smoothData(originalData, windowValue);

        System.out.println("\nSmoothed Data:");
        smoothedData.forEach(System.out::println);

        // dump the smoothed result to a new file
        Plotter.exportCSV(smoothedData, "smoothed_output.csv");
    }

    // grabs the data from CSV and skips the header
    public static List<Point> loadCSV(String filename) {
        List<Point> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) { skipHeader = false; continue; }
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0].trim());
                double y = Double.parseDouble(parts[1].trim());
                data.add(new Point(x, y));
            }
        } catch (IOException e) {
            System.err.println("Couldn't read the file: " + e.getMessage());
            return null;
        }
        return data;
    }

    // takes each point and replaces its y with the average of nearby y's
    public static List<Point> smoothData(List<Point> original, int window) {
        List<Point> smoothed = new ArrayList<>();

        for (int i = 0; i < original.size(); i++) {
            double sum = 0;
            int count = 0;

            // look at the neighbors on both sides
            for (int j = i - window; j <= i + window; j++) {
                if (j >= 0 && j < original.size()) {
                    sum += original.get(j).y;
                    count++;
                }
            }

            double avgY = sum / count;

            // keep x the same, swap in the smoothed y
            smoothed.add(new Point(original.get(i).x, avgY));
        }

        return smoothed;
    }
}
