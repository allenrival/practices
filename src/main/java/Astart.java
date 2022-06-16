import java.util.HashSet;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年05月17日 17:01
 */
public class Astart {
    int x;
    int y;

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.contains(5);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Astart astart = (Astart) o;

        if (x != astart.x) return false;
        return y == astart.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
