package HashStuff;

import CSVStuff.DataHandler;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class HashMapTester {
    public static void main(String[] args) {
        XYSeries addTimeSeries = new XYSeries("Addition Time");
        XYSeries collisionSeries = new XYSeries("Collisions");

        BetterHash map = new BetterHash();

        // Load CSV and verify it exists
        String csvPath = "src/HashStuff/NBA_Player_Names.csv";
        File file = new File(csvPath);
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return;
        }

        // Read the CSV file
        List<String> names = DataHandler.loadCSV(csvPath);
        if (names == null || names.isEmpty()) {
            System.out.println("No data loaded from CSV");
            return;
        }

        // Test hash implementation
        for (int i = 0; i < names.size(); i++) {
            String value = names.get(i);

            long startTime = System.nanoTime();
            map.add(value);
            long endTime = System.nanoTime();

            double timeInMs = (endTime - startTime) / 1_000_000.0;
            int collisions = map.getCollisions();

            addTimeSeries.add(i, timeInMs);
            collisionSeries.add(i, collisions);
        }

        createChart(addTimeSeries, "Addition Time", "Operations", "Time (ms)");
        createChart(collisionSeries, "Collisions", "Operations", "Number of Collisions");
        graphNameLengths(map);
    }

    private static void createChart(XYSeries series, String title, String xLabel, String yLabel) {
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(title, xLabel, yLabel, dataset);
        ChartFrame frame = new ChartFrame(title, chart);
        frame.pack();
        frame.setVisible(true);
    }

    private static void graphNameLengths(BetterHash map) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Get internal data structure from BetterHash
        LinkedList<String>[] data = map.getData();

        // For each bucket, calculate average name length
        for (int i = 0; i < data.length; i++) {
            if (data[i].isEmpty()) continue;

            double avgLength = data[i].stream()
                    .mapToInt(String::length)
                    .average()
                    .orElse(0);

            dataset.addValue(avgLength, "Average Name Length", "Bucket " + i);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Name Lengths by Bucket",
                "Bucket",
                "Average Length",
                dataset
        );

        ChartFrame frame = new ChartFrame("Name Lengths Distribution", chart);
        frame.pack();
        frame.setVisible(true);
    }
}