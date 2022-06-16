package pointcloud;

import java.util.ArrayList;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年04月06日 8:15
 */
public class Main02 {
    static ArrayList<Point> result = new ArrayList<>();
    static Double min=Double.MAX_VALUE;
    public static void main(String[] args) {
        Point[] point1 =new Point[16];
        ArrayList<Point> points1 = new ArrayList<>();
        point1[0]=new Point(69,69,158);//模拟拍塔身
        point1[1]=new Point(68,67,133);//模拟拍塔头
        points1.add(new Point(69.311432, 37.580002, 128.339996));
        point1[2]=new Point(69.311432, 37.580002, 128.339996);
        points1.add(new Point(68.400002, 37.279999, 125.320000));
        point1[3]=new Point(68.400002, 37.279999, 125.320000);
        points1.add(new Point(68.440002, 37.251427, 123.900002));
        point1[4]=new Point(68.440002, 37.251427, 123.900002);
        points1.add(new Point(68.488571, 37.340000, 122.720001));
        point1[5]=new Point(68.488571, 37.340000, 122.720001);
        points1.add(new Point(69.371429, 37.411430, 118.671425));
        point1[6]=new Point(69.371429, 37.411430, 118.671425);
        point1[7]=new Point(69, 40, 130);//模拟辅助点
        points1.add(new Point(69.400002, 37.439999, 117.160004));
        point1[8]=new Point(69.400002, 37.439999, 117.160004);
        points1.add(new Point(69.171425, 37.511429, 115.988571));
        point1[9]=new Point(69.171425, 37.511429, 115.988571);
        point1[10]=new Point(70, 42, 125);//模拟辅助点
        points1.add(new Point(68.471428, 37.139999, 112.351425));
        point1[11]=new Point(68.471428, 37.139999, 112.351425);
        points1.add(new Point(68.451431, 37.211430, 110.925713));
        point1[12]=new Point(68.451431, 37.211430, 110.925713);
        points1.add(new Point(68.559998, 37.171429, 109.611427));
        point1[13]=new Point(68.559998, 37.171429, 109.611427);
        point1[14]=new Point(68, 33, 90);//模拟拍摄航道
        point1[15]=new Point(70,32,160);//模拟结束点
        boolean[] flag = new boolean[point1.length];
        flag[2]=true;
        flag[3]=true;
        flag[4]=true;
        flag[5]=true;
        flag[6]=true;
        flag[8]=true;
        flag[9]=true;
        flag[11]=true;
        flag[12]=true;
        flag[13]=true;
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
        dfs(points1,points2,new int[point1.length],0, new ArrayList<>(),0.0);
        Point[] point2=new Point[point1.length];
        int k=0;
        for (int i = 0; i < result.size(); i++) {
            while(!flag[k]){
                k++;
            }
            point2[k]=result.get(i);
            System.out.println(point2[k].x+" "+point2[k].y+" "+point2[k].z);
            k++;
        }
        Point findpoint = findpoint(point1, point2, flag);
        k=0;
        for (int i = 0; i < result.size(); i++) {
            while(!flag[k]){
                point2[k]=point1[k].add(findpoint);
                System.out.println(point2[k].x+" "+point2[k].y+" "+point2[k].z);
                k++;
            }
            System.out.println(point2[k].x+" "+point2[k].y+" "+point2[k].z);
            k++;
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
    public static Point findpoint(Point[] point1,Point[] point2,boolean[] flag){
        double x=0.0;
        double y=0.0;
        double z=0.0;
        double count=0;
        for(int i=0;i<point1.length;i++){
            if(flag[i]){
                count++;
                x+=point2[i].x-point1[i].x;
                y+=point2[i].y-point1[i].y;
                z+=point2[i].z-point1[i].z;
            }
        }
        x/=count;
        y/=count;
        z/=count;
        return new Point(x,y,z);
    }
}
