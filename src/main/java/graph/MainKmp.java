package graph;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月28日 9:56
 */
public class MainKmp {
    public static void main(String[] args) {
        String s="aasdadasdc";
        String t="asdc";
        int[] next=getNext(t);
        int i=0;
        int j=0;
        while(i<s.length()&&j<t.length()){
            if(j==-1||s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else j=next[j];
        }
        if(j==t.length()){
            System.out.println(i-j);
        }else System.out.println(-1);
    }
    public static int[] getNext(String t){
        int[] next=new int[t.length()];
        int i=0;
        int j=-1;
        next[0]=-1;
        while(i<t.length()-1){
            if(j==-1||t.charAt(i)==t.charAt(j)){
                i++;
                j++;
                next[i]=j;
            }else j=next[j];
        }
        return next;
    }
}
