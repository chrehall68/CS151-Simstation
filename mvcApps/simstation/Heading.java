package simstation;

public class Heading {
    private final double xDir;
    private final double yDir;

    public Heading(double xDir, double yDir) {
        // convert to unit
        // x^2 + y^2 should equal 1
        double total = Math.pow(xDir, 2) + Math.pow(yDir, 2);

        // x^2 + y^2 = total
        // x^2/total + y^2/total = 1
        // (x/sqrt(total))^2 + (y/sqrt(total))^2 = 1
        double sqrt = Math.sqrt(total);
        this.xDir = xDir / sqrt;
        this.yDir = yDir / sqrt;
    }

    public double getxDir() {
        return xDir;
    }

    public double getyDir() {
        return yDir;
    }

    public static Heading random() {
        double xDir = Math.random();
        double yDir = Math.sqrt(1 - Math.pow(xDir, 2));

        double xMul = Math.random() < 0.5 ? 1 : -1;
        double yMul = Math.random() < 0.5 ? 1 : -1;
        return new Heading(xDir * xMul, yDir * yMul);
    }
}