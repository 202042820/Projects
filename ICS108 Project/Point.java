public class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        x = 0;
        y = 0;
    }

    public double xGetter() {
        return x;
    }

    public double yGetter() {
        return y;
    }

    public double distance(Point p) {
        double totalDistance = Math.sqrt(Math.pow(p.xGetter(), p.yGetter()) + Math.pow(x, y));
        return totalDistance;
    }

}
