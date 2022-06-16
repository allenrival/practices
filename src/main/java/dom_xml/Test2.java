package dom_xml;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月07日 16:04
 */
public class Test2 {
    public static void main(String[] args) {
        double x1=111.37373620551186;
        double y1=30.45376262387069;
        double z1=177.05465370085815;
        double x2=111.37371066738945;
        double y2=30.45376108444034;
        double z2=177.6816761892995;
        double m1=Math.pow(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)+Math.pow(z1-z2,2),0.5);
        System.out.println(m1);
    }
}
