public class FreeFallCalculator {
    public static double calculatePosition(double t, double a, double v0, double x0) {
        return 0.5 * a * t * t + v0 * t + x0;
    }

    public static void main(String[] args) {
        double a = -9.81;
        double x0 = 257;
        double v0 = 63 * 1000 / 3600.0;
        double t = 2.2;

        double position = calculatePosition(t, a, v0, x0);
        System.out.printf("Pozycja ciała po czasie %.2f s wynosi %.2f m%n", t, position);
    }
}