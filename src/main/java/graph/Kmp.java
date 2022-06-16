package graph;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月28日 9:27
 */
public class Kmp {
    public static void main(String[] args) {
        String s = "abababac";
        String t = "abac";
        System.out.println(kmp(s, t));
    }
    public static int kmp(String s, String t) {
        int[] next = getNext(t);
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (j == -1 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == t.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int[] getNext(String t) {
        int[] next = new int[t.length()];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < t.length() - 1) {
            if (j == -1 || t.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
