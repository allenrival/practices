package pointcloud;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年04月06日 8:30
 */
public class Point {
    double x;
    double y;
    double z;
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point add(Point p) {
        return new Point(x + p.x, y + p.y, z + p.z);
    }
}
