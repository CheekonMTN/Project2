package JFreePlusApache;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.awt.Color;
import java.util.Random;
import org.apache.commons.math4.legacy.stat.descriptive.DescriptiveStatistics;

public class JFAPlotter {
    public static XYSeries generateNoisyData(int numPoints, double saltAmount) {
        XYSeries data = new XYSeries("Original Data");
        Random rand = new Random();

        for (int i = 0; i < numPoints; i++) {
            double x = i;
            double y = Math.sin(x/10.0) * 100; // base function
            y += (rand.nextDouble() * 2 - 1) * saltAmount; // add noise
            data.add(x, y);
        }
        return data;
    }

    public static XYSeries smoothData(XYSeries data, int windowSize) {
        XYSeries smoothed = new XYSeries("Smoothed Data");
        DescriptiveStatistics stats = new DescriptiveStatistics(windowSize);

        for (int i = 0; i < data.getItemCount(); i++) {
            stats.clear();

            // Fill the window
            for (int j = Math.max(0, i - windowSize/2);
                 j < Math.min(data.getItemCount(), i + windowSize/2 + 1); j++) {
                stats.addValue(data.getY(j).doubleValue());
            }

            // Add smoothed point
            smoothed.add(data.getX(i), stats.getMean());
        }
        return smoothed;
    }

    public static void plotData(XYSeries original, XYSeries smoothed) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(original);
        dataset.addSeries(smoothed);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Data Smoothing Visualization",
                "X",
                "Y",
                dataset
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        XYSeries original = generateNoisyData(100, 20);
        XYSeries smoothed = smoothData(original, 15);
        plotData(original, smoothed);
    }
}