package CSVStuff;

import java.util.*;

public class Salter {
    public static List<Point> saltData(List<Point> original, double min, double max) {
        List<Point> salted = new ArrayList<>();
        Random rand = new Random();

        for (Point p : original) {
            double salt = min + (max - min) * rand.nextDouble();
            salted.add(new Point(p.x, p.y + salt));
        }
        return salted;
    }
}