import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年05月22日 10:27
 */
class TextEditor {

    StringBuilder sb;
    int index;

    public TextEditor() {
        sb=new StringBuilder();
        index = 0;
    }

    public void addText(String text) {
        sb=sb.replace(index,index,text);
        index+=text.length();
    }

    public int deleteText(int k) {
        int temp=Math.max(0,index-k);
        if(temp==0){
            int res=index;
            sb= sb.replace(0,index, "");
            index=0;
            return res;
        }
        sb=sb.replace(index-k, index,"");
        index-=k;
        return k;
    }

    public String cursorLeft(int k) {
        index=Math.max(0,index-k);
        return sb.substring(Math.max(0,index-10),index);
    }

    public String cursorRight(int k) {
        index=Math.min(index+k,sb.length());
        return sb.substring(Math.max(0,index-10),index);
    }
}