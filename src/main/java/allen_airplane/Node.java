package allen_airplane;

import lombok.Data;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年05月09日 17:04
 */
@Data
public class Node {
    private int x;
    private int y;
    private int z;
    private int cost;

    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (x != node.x) return false;
        if (y != node.y) return false;
        return z == node.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
