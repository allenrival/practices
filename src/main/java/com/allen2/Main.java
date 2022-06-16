package com.allen2;

import com.allen.Node;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2021年12月28日 11:33
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Node[] frameNodes=initFrameArray("c:/Users/Administrator/Desktop/麂友一回#002.kml");
        Node[] testNodes=initTestArray("c:/Users/Administrator/Desktop/麂友一回#007.kml");
        Node[] result=new Node[testNodes.length];
        result[0]=testNodes[0];
        HashSet<Node> set=new HashSet<>();
        for(int i=5;i<testNodes.length;i++){
            set.add(testNodes[i]);
        }
        for(int i=5;i<testNodes.length;i++){
            Node node=frameNodes[i];
            Node node1=frameNodes[i-1];
            double x=testNodes[i-1].x+(node.x- node1.x);
            double y=testNodes[i-1].y+(node.y- node1.y);
            double z=testNodes[i-1].z+(node.z- node1.z);
            Iterator<Node> it = set.iterator();
            double min=Double.MAX_VALUE;
            Node node2=null;
            while (it.hasNext()){
                Node temp=it.next();
                double t=Math.pow(Math.pow(x- temp.x,2)+Math.pow(y- temp.y,2)+Math.pow(z-temp.z,2),0.5);
                if(t<min){
                    min=t;
                    node2=temp;
                }
            }
            result[i]=node2;
            set.remove(node2);
        }
        for(int i=5;i<result.length;i++){
            System.out.println(result[i].id);
        }
    }
    public static Node[] initFrameArray(String framepath) throws IOException {
        //读取航线框架文件kml
        FileReader in=new FileReader(framepath);
        StringBuilder sb=new StringBuilder();
        char[] buffer=new char[2048];

        while(in.read(buffer)!=-1){
            sb.append(buffer);
        }
        //正则表达式匹配内容，把点坐标提取出来
        String content= sb.toString();
        String pattern="(.*)(<coordinates>.*</coordinates>)";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        if(matcher.find()){
            content=matcher.group(2);
        }
        content=content.substring(13,content.length()-14);
        String[] eachpoint=content.split(" ");
        //将点坐标放入列表中
        Node[]framenodes=new Node[eachpoint.length];
        for(int i=0;i<eachpoint.length;i++){
            String[] xyz=eachpoint[i].split(",");
            Node node=new Node(i,Double.parseDouble(xyz[0]),Double.parseDouble(xyz[1]),Double.parseDouble(xyz[2]));
            framenodes[i]=node;
        }
        return framenodes;
    }

    /**
     * 读取根据绝缘子串坐标计算出来的航点，将航点坐标放入列表。
     * @param testpath 测试航点
     */
    public static Node[] initTestArray(String testpath) throws IOException {
        FileReader in=new FileReader(testpath);
        StringBuilder sb=new StringBuilder();
        char[] buffer=new char[2048];
        while(in.read(buffer)!=-1){
            sb.append(buffer);
        }
        //正则表达式匹配内容，把点坐标提取出来
        String content= sb.toString();
        String pattern="(.*)(<coordinates>.*</coordinates>)";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        if(matcher.find()){
            content=matcher.group(2);
        }
        content=content.substring(13,content.length()-14);
        String[] eachpoint=content.split(" ");
        //将点坐标放入列表中
        Node[] testnodes=new Node[eachpoint.length];
        for(int i=0;i<eachpoint.length;i++) {
            String[] xyz = eachpoint[i].split(",");
            Node node = new Node(i, Double.parseDouble(xyz[0]), Double.parseDouble(xyz[1]), Double.parseDouble(xyz[2]));
            testnodes[i] = node;
        }
        return testnodes;
    }
}
