package pointcloud;

import java.util.ArrayList;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月30日 8:22
 */
public class Main01 {
    static ArrayList<Point> result = new ArrayList<>();
    static Double min=Double.MAX_VALUE;
    public static void main(String[] args) {
        ArrayList<Point> points1 = new ArrayList<>();
        points1.add(new Point(69.311432, 37.580002, 128.339996));
        points1.add(new Point(68.400002, 37.279999, 125.320000));
        points1.add(new Point(68.440002, 37.251427, 123.900002));
        points1.add(new Point(68.488571, 37.340000, 122.720001));
        points1.add(new Point(69.371429, 37.411430, 118.671425));
        points1.add(new Point(69.400002, 37.439999, 117.160004));
        points1.add(new Point(69.171425, 37.511429, 115.988571));
        points1.add(new Point(68.471428, 37.139999, 112.351425));
        points1.add(new Point(68.451431, 37.211430, 110.925713));
        points1.add(new Point(68.559998, 37.171429, 109.611427));
        ArrayList<Point> points2 = new ArrayList<>();
        points2.add(new Point(58.111427, 35.540001, 109.620003));
        points2.add(new Point(58.311428, 35.340000, 112.019997));
        points2.add(new Point(58.171429, 35.299999, 110.774284));
        points2.add(new Point(57.351429, 35.180000, 128.288574));
        points2.add(new Point(58.139999, 35.351429, 122.559998));
        points2.add(new Point(57.500000, 35.128571, 118.528572));
        points2.add(new Point(57.139999, 35.188572, 115.940002));
        points2.add(new Point(57.511429, 35.128571, 117.225716));
        points2.add(new Point(58.488571, 35.279999, 125.182854));
        points2.add(new Point(58.220001, 35.328571, 123.874283));
        dfs(points1,points2,new int[points2.size()],0,new ArrayList<Point>(),0.0);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).x+" "+result.get(i).y+" "+result.get(i).z);
        }
    }
    public static void dfs(ArrayList<Point> points1, ArrayList<Point> points2,int[] visited1,int i,ArrayList<Point> temp,Double tempmin){
        if(i==points1.size()){
            if(tempmin<min&&temp.size()==points2.size()){
                min=tempmin;
                result=new ArrayList<>(temp);
            }
            return;
        }
        for(int j=0;j<points2.size();j++){
            if(visited1[j]==0){
                temp.add(points2.get(j));
                visited1[j]=1;
                tempmin+=Math.sqrt(Math.pow(points2.get(j).x-points1.get(i).x,2)+Math.pow(points2.get(j).y-points1.get(i).y,2)+Math.pow(points2.get(j).z-points1.get(i).z,2));
                dfs(points1,points2,visited1,i+1,temp,tempmin);
                temp.remove(temp.size()-1);
                visited1[j]=0;
                tempmin-=Math.sqrt(Math.pow(points2.get(j).x-points1.get(i).x,2)+Math.pow(points2.get(j).y-points1.get(i).y,2)+Math.pow(points2.get(j).z-points1.get(i).z,2));
            }
        }
    }

}
