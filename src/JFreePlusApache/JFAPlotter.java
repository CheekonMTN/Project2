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
    public static XYSeries generatePureData() {
        XYSeries data = new XYSeries("Pure Function");
        for (double i = -3; i <= 3; i += 0.1) {
            double x = i;
            double y = Math.pow(x, 3) - 2 * x;
            data.add(x, y);
        }
        return data;
    }

    public static XYSeries generateNoisyData(int numPoints, double saltAmount) {
        XYSeries data = new XYSeries("Noisy Data");
        Random rand = new Random();

        for (double i = -3; i <= 3; i += 0.1) {
            double x = i;
            double y = Math.pow(x, 3) - 2 * x;
            y += (rand.nextDouble() * 2 - 1) * saltAmount;
            data.add(x, y);
        }
        return data;
    }

    public static XYSeries smoothData(XYSeries data, int windowSize) {
        XYSeries smoothed = new XYSeries("Smoothed Data");
        DescriptiveStatistics stats = new DescriptiveStatistics(windowSize);

        for (int i = 0; i < data.getItemCount(); i++) {
            stats.clear();
            for (int j = Math.max(0, i - windowSize/2);
                 j < Math.min(data.getItemCount(), i + windowSize/2 + 1); j++) {
                stats.addValue(data.getY(j).doubleValue());
            }
            smoothed.add(data.getX(i), stats.getMean());
        }
        return smoothed;
    }

    public static void plotData(XYSeries pure, XYSeries noisy, XYSeries smoothed) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(pure);
        dataset.addSeries(noisy);
        dataset.addSeries(smoothed);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Data Smoothing Visualization",
                "X",
                "Y",
                dataset
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.RED);
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
        XYSeries pure = generatePureData();
        XYSeries noisy = generateNoisyData(100, 1.0);
        XYSeries smoothed = smoothData(noisy, 25);
        plotData(pure, noisy, smoothed);
    }
}