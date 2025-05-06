package Misc;

import java.math.BigInteger;

public class StatsLibrary {

    // Simple Poisson calculation
    public double poisson(double mean, int events) {
        // Check if input is valid
        if (events < 0 || mean <= 0) {
            return 0.0;
        }

        // Calculate factorial first, similar to Project One implementation
        BigInteger factorial;
        if (events <= 1) {
            factorial = BigInteger.ONE;
        } else {
            factorial = BigInteger.ONE;
            for (int i = 2; i <= events; ++i) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }
        }

        // Calculate Poisson probability
        double top = Math.pow(mean, events) * Math.exp(-mean);
        double answer = top / factorial.doubleValue();
        return answer;
    }

    // Chebyshev calculation
    public double tchebysheff(double k) {
        // Check if k is valid
        if (k <= 0) {
            return 0.0;
        }

        // Calculate minimum percentage
        double answer = 1 - (1 / (k * k));
        return answer;
    }

    // Get interval range
    public double[] getRange(double mean, double std, double k) {
        double[] range = new double[2];

        // Calculate lower and upper bounds
        range[0] = mean - (k * std);
        range[1] = mean + (k * std);

        return range;
    }
}