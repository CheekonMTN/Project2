package CSVStuff;

import java.io.*;
import java.util.*;

public class Salter {

    // class that holds x and y values
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
        String filename = "data.csv";  // name of the input file
        double minSalt = -1.0;         // smallest value we can add/subtract
        double maxSalt = 1.0;          // biggest value we can add/subtract

        // grab the original data from the file
        List<Point> originalData = loadCSV(filename);
        if (originalData == null) return;

        System.out.println("Original Data:");
        originalData.forEach(System.out::println);

        // do the salting and get a brand new list
        List<Point> saltedData = saltData(originalData, minSalt, maxSalt);

        System.out.println("\nSalted Data:");
        saltedData.forEach(System.out::println);

        // save the new salted stuff to a file
        Plotter.exportCSV(saltedData, "salted_output.csv");
    }

    // reads x,y from the CSV and loads it into a list
    public static List<Point> loadCSV(String filename) {
        List<Point> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0].trim());
                double y = Double.parseDouble(parts[1].trim());
                data.add(new Point(x, y)); // add the point to the list
            }
        } catch (IOException e) {
            System.err.println("Couldn't read the file: " + e.getMessage());
            return null;
        }
        return data;
    }

    // takes the original list and makes a new one with salty Y values
    public static List<Point> saltData(List<Point> original, double minSalt, double maxSalt) {
        List<Point> salted = new ArrayList<>();
        Random rand = new Random();
        int i = 0;

        // loop through everything using while, just 'cause
        while (i < original.size()) {
            Point p = original.get(i);

            // make a random salt value in the given range
            double salt = minSalt + (maxSalt - minSalt) * rand.nextDouble();

            // flip a coin to decide if we add or subtract
            boolean addSalt = rand.nextBoolean();
            double newY = addSalt ? p.y + salt : p.y - salt;

            // keep x the same, change y — toss the new one in the list
            salted.add(new Point(p.x, newY));

            i++;
        }

        return salted;
    }
}

