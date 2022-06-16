package graph.pratice;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月29日 15:13
 */
public class Main01kml {
    public static void main(String[] args) {
        String s="adsdadadadsad";
        String t="adsa";
        int[] next=getNext(t);
        int i=0;
        int j=0;
        while(i<s.length()){
            if(j==-1||s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else j=next[j];
            if(j==t.length()){
                System.out.println(i-j);
                return;
            }
        }
        System.out.println(-1);
    }
    public static int[] getNext(String t){
        int i=0;
        int j=-1;
        int[] next=new int[t.length()];
        next[0]=-1;
        while(i<t.length()-1){
            if(j==-1||t.charAt(i)==t.charAt(j)){
                i++;
                j++;
                next[i]=j;
            }else{
                j=next[j];
            }
        }
        return next;
    }
}
