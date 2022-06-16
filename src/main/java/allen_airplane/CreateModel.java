package allen_airplane;

import java.io.*;

/**
 * @Description: TODO 读取模型文件，并创建模型对象
 * @author: allen
 * @date: 2022年05月09日 14:33
 */
public class CreateModel {
    //测试
    public static void main(String[] args) throws IOException {
        Model model=createModel("src/main/resources/model.txt");
        int miss=0;
        int count=0;
        for(int i=0;i<model.getModel().length;i++) {
            for(int j=0;j<model.getModel()[i].length;j++) {
                for(int k=0;k<model.getModel()[i][j].length;k++) {
                    if (model.getModel()[i][j][k] == 1) {
                        count++;
                    }
                    if(model.getModel()[i][j][k]!=-1&&model.getModel()[i][j][k]!=1&&model.getModel()[i][j][k]!=0){
                        miss++;
                    }
                }
            }
        }
        System.out.println(count);
        model=improvedmodel(model,3);
        int count1=0;
        for(int i=0;i<model.getModel().length;i++) {
            for(int j=0;j<model.getModel()[i].length;j++) {
                for(int k=0;k<model.getModel()[i][j].length;k++) {
                    if (model.getModel()[i][j][k] == 1) {
                        count1++;
                    }
                    if(model.getModel()[i][j][k]!=-1&&model.getModel()[i][j][k]!=1&&model.getModel()[i][j][k]!=0){
                        miss++;
                    }
                }
            }
        }
        System.out.println(count1);
        System.out.println(miss);
    }
    public static Model createModel(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        reader.readLine(); //读取第一行数据，不用
        String line = reader.readLine(); //读取第二行数据，塔的点数
        int towerPoint=Integer.parseInt(line.split(":")[1].trim());
        line=reader.readLine(); //读取第三行数据，AABB盒子的最小点坐标
        String[] minpoint=line.split(" ");
        int minX=Integer.parseInt(minpoint[0]);
        int minY=Integer.parseInt(minpoint[1]);
        int minZ=Integer.parseInt(minpoint[2]);
        line=reader.readLine(); //读取第四行数据，AABB盒子的最大点坐标
        String[] maxpoint=line.split(" ");
        int maxX=Integer.parseInt(maxpoint[0]);
        int maxY=Integer.parseInt(maxpoint[1]);
        int maxZ=Integer.parseInt(maxpoint[2]);
        int[][][] model = new int[maxX-minX+1][maxY-minY+1][maxZ-minZ+1];
        reader.readLine(); //读取第五行数据，不用
        for(int i=0;i<towerPoint;i++) {
            if (i == 0 || i == towerPoint - 1) {
                line = reader.readLine(); //第一个和最后一个数据不用
            } else {
                line = reader.readLine(); //读取第i+5+1行数据，塔的点坐标
                String[] point=line.split(" ");
                int x=Integer.parseInt(point[0]);
                int y=Integer.parseInt(point[1]);
                int z=Integer.parseInt(point[2]);
                model[x-minX][y-minY][z-minZ]=1;
            }
        }
        reader.readLine(); //读取第6+towerPoint行数据，不用
        //此处的20是有20个航点信息，被写死了，后面可以修改为读取的数据
        for(int i=0;i<20;i++){
            line=reader.readLine();//读取第i+towerPoint+7行数据，航点坐标
            String[] point=line.split(" ");
            int x=Integer.parseInt(point[0]);
            int y=Integer.parseInt(point[1]);
            int z=Integer.parseInt(point[2]);
            model[x-minX][y-minY][z-minZ]=-1;
        }
        return new Model(minX,minY,minZ,model);
    }

    /**
     *
     * @param model
     * @param length 膨胀系数
     * @return
     */
    public static Model improvedmodel(Model model,int length){
        //周围26个格子
        int[][] path=new int[26][3];
        int t=0;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                for(int k=-1;k<=1;k++){
                    if(i==0&&j==0&&k==0){
                        continue;
                    }
                    path[t][0]=i;
                    path[t][1]=j;
                    path[t][2]=k;
                }
            }
        }
        for(int w=0;w<length;w++){
            for(int i=0;i<model.getModel().length;i++){
                for(int j=0;j<model.getModel()[0].length;j++){
                    for(int k=0;k<model.getModel()[0][0].length;k++){
                        if(w==length-1){
                            if (model.getModel()[i][j][k]==w+1){
                                model.getModel()[i][j][k]=1;
                                for(int l=0;l<path.length;l++){
                                    int x=i+path[l][0];
                                    int y=j+path[l][1];
                                    int z=k+path[l][2];
                                    if(x>=0&&x<model.getModel().length&&y>=0&&y<model.getModel()[0].length&&z>=0&&z<model.getModel()[0][0].length){
                                        if (model.getModel()[x][y][z] == 0) {
                                            model.getModel()[x][y][z] = 1;
                                        }
                                    }
                                }
                            }else if(model.getModel()[i][j][k]!=0&&model.getModel()[i][j][k]!=-1){
                                model.getModel()[i][j][k]=1;
                            }
                        }
                        else {
                            if (model.getModel()[i][j][k] == w + 1) {
                                for (int l = 0; l < path.length; l++) {
                                    int x = i + path[l][0];
                                    int y = j + path[l][1];
                                    int z = k + path[l][2];
                                    if (x >= 0 && x < model.getModel().length && y >= 0 && y < model.getModel()[0].length && z >= 0 && z < model.getModel()[0][0].length && model.getModel()[x][y][z] == 0) {
                                        if (model.getModel()[x][y][z] == 0) {
                                            model.getModel()[x][y][z] = w + 2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return model;
    }
}
