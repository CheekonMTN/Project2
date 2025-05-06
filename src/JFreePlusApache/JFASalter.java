package JFreePlusApache;

import org.jfree.data.xy.XYSeries;
import java.util.Random;

public class JFASalter{
    public static XYSeries generateAndSaltData(int numPoints, double saltAmount) {
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
}