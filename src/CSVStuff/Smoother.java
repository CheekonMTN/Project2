package CSVStuff;

import java.io.*;
import java.util.*;

public class Smoother {
    public static void main(String[] args) {
        String filename = "salted_sine.csv";
        int windowValue = 5;

        List<Point> originalData = loadCSV(filename);
        if (originalData == null) return;

        List<Point> smoothedData = smoothData(originalData, windowValue);
        Plotter.exportCSV(smoothedData, "smoothed_sine.csv");
    }

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

    public static List<Point> smoothData(List<Point> original, int window) {
        List<Point> smoothed = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            double sum = 0;
            int count = 0;
            for (int j = Math.max(0, i - window); j <= Math.min(original.size() - 1, i + window); j++) {
                sum += original.get(j).y;
                count++;
            }
            smoothed.add(new Point(original.get(i).x, sum / count));
        }
        return smoothed;
    }
}