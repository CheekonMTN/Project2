package CSVStuff;

import CSVStuff.Salter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Plotter {

    // takes a list of points and dumps them into a CSV file
    public static void exportCSV(List<Salter.Point> data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X,Y\n"); // add header so Excel doesn’t get confused
            for (Salter.Point point : data) {
                writer.write(point.x + "," + point.y + "\n");
            }
            System.out.println("Saved to: " + filename);
        } catch (IOException e) {
            System.err.println("Couldn't write file: " + e.getMessage());
        }
    }
}

