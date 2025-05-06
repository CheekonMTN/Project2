package JFreePlusApache;

import org.apache.commons.math4.legacy.stat.descriptive.DescriptiveStatistics;

public class ApacheSmooth {
    public static void main(String[] args) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        stats.addValue(10);
        stats.addValue(20);
        stats.addValue(30);
        System.out.println("Mean: " + stats.getMean());
    }
}
