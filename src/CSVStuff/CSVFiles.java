package CSVStuff;

import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.sin;

public class CSVFiles {


    public static double computeY(double x) {
        return sin(x);
    }

    public static void main(String[] args) {
        String fileName = "output.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("X,Y\n");

            for (int x = -10; x <= 10; x++) {
                double y = computeY(x);
                writer.append(x + "," + y + "\n");
            }

            System.out.println("CSV file created: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }
    }
}
