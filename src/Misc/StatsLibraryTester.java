package Misc;

public class StatsLibraryTester {
    public static void main(String[] args) {
        // Create our stats library
        StatsLibrary stats = new StatsLibrary();

        // Test Poisson with some examples
        System.out.println("Testing Poisson Distribution:");
        System.out.println("Mean = 3, Events = 2: " + stats.poisson(3, 2));
        System.out.println("Mean = 5, Events = 4: " + stats.poisson(5, 4));
        System.out.println("Mean = 2, Events = 0: " + stats.poisson(2, 0));
        System.out.println();

        // Test Chebyshev with different k values
        System.out.println("Testing Chebyshev's Theorem:");
        System.out.println("k = 2: " + stats.tchebysheff(2));
        System.out.println("k = 3: " + stats.tchebysheff(3));
        System.out.println("k = 4: " + stats.tchebysheff(4));
        System.out.println();

        // Test range calculations
        System.out.println("Testing Range Calculations:");
        double[] range1 = stats.getRange(10, 2, 2);
        System.out.println("Mean = 10, StdDev = 2, k = 2");
        System.out.printf("Range: [%.2f, %.2f]\n", range1[0], range1[1]);

        double[] range2 = stats.getRange(50, 5, 3);
        System.out.println("Mean = 50, StdDev = 5, k = 3");
        System.out.printf("Range: [%.2f, %.2f]\n", range2[0], range2[1]);
    }
}