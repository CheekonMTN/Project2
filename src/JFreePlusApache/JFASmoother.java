package JFreePlusApache;

import org.apache.commons.math4.legacy.stat.descriptive.DescriptiveStatistics;
import org.jfree.data.xy.XYSeries;

public class JFASmoother {
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
}