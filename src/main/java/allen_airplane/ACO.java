package allen_airplane;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;

/**
 * @Description: TODO ACO算法
 * @author: allen
 * @date: 2022年05月16日 10:02
 */
@Data
public class ACO {
    private Ant[] ants; // 蚂蚁
    private int antNum; // 蚂蚁数量
    private int pointNum; // 点数量
    private int MAX_GEN; // 运行代数
    private double[][] pheromone; // 信息素矩阵
    private int[][] distance; // 距离矩阵
    private int bestLength; // 最佳长度
    private int[] bestTour; // 最佳路径

    // 三个参数
    private double alpha;    // 信息素重要程度
    private double beta;     // 距离重要程度
    private double rho;      // 蚂蚁搜索持续概率

    public ACO(int m, int g, float a, float b, float r) {
        antNum = m;
        ants = new Ant[antNum];
        MAX_GEN = g;
        alpha = a;
        beta = b;
        rho = r;

    }

    public static void main(String[] args) throws IOException {
        ACO aco=new ACO(100, 1000, 1.f, 5.f, 0.5f);
        aco.init("src/main/resources/result.txt");
        aco.solve();
    }

    /**
     * 初始化
     * @param filename 文件名
     */
    private void init(String filename) throws IOException {
        //获取距离矩阵
        BufferedReader br=new BufferedReader(new FileReader(filename));
        String line;
        pointNum=Integer.parseInt(br.readLine());
        distance=new int[pointNum][pointNum];
        ArrayList<Node>[][] path=new ArrayList[pointNum][pointNum];//路径矩阵
        for(int i=0;i<pointNum;i++){
            for (int j=i+1;j<pointNum;j++){
                if(i==j){
                    distance[i][j]=0;
                }
                else{
                    line=br.readLine();
                    distance[i][j]=Integer.parseInt(line.split(" ")[2]);
                    distance[j][i]=distance[i][j];
                    line=br.readLine();
                    int length=Integer.parseInt(line.split(":")[1]);
                    path[i][j]=new ArrayList<>();
                    for(int k=0;k<length;k++){
                        line=br.readLine();
                        String[] str=line.split(" ");
                        path[i][j].add(new Node(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2])));
                    }
                }
            }
        }

        // 初始化信息素矩阵
        pheromone = new double[pointNum][pointNum];
        for (int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                pheromone[i][j] = 0.1; // 初始化为0.1
            }
        }
        bestLength = Integer.MAX_VALUE;
        bestTour = new int[pointNum];
        // 随机放置蚂蚁
        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant(pointNum);
            ants[i].init(distance, alpha, beta);
        }
    }
    
    public void solve() {
        for (int g = 0; g < MAX_GEN; g++) {
            for (int i = 0; i < antNum; i++) {
                for (int j = 1; j < pointNum; j++) {
                    ants[i].selectNextPoint(pheromone);
                }
                ants[i].getUsedPoint().add(ants[i].getFirstPoint());
                ants[i].calculateTourLength();
                if (ants[i].getTourLength() < bestLength) {
                    bestLength = ants[i].getTourLength();
                    for (int k = 0; k < pointNum; k++) {
                        bestTour[k] = ants[i].getUsedPoint().get(k);
                    }
                }
                for (int j = 0; j < pointNum; j++) {
                    ants[i].getDelta()[ants[i].getUsedPoint().get(j)][ants[i].getUsedPoint().get(j + 1)] = (1.0/ ants[i].getTourLength());
                    ants[i].getDelta()[ants[i].getUsedPoint().get(j + 1)][ants[i].getUsedPoint().get(j)] = (1.0/ ants[i].getTourLength());
                }
            }

            // 更新信息素
            updatePheromone();

            // 重新初始化蚂蚁
            for (int i = 0; i < antNum; i++) {
                ants[i].init(distance, alpha, beta);
            }
        }
        // 打印最佳结果
        printOptimal();
    }
    // 更新信息素
    private void updatePheromone() {
        // 信息素挥发
        for (int i = 0; i < pointNum; i++)
            for (int j = 0; j < pointNum; j++)
                pheromone[i][j] = pheromone[i][j] * (1 - rho);
        // 信息素更新
        for (int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                for (int k = 0; k < antNum; k++) {
                    pheromone[i][j] += ants[k].getDelta()[i][j];
                }
            }
        }
    }

    private void printOptimal() {
        System.out.println("The optimal length is: " + bestLength);
        System.out.println("The optimal tour is: ");
        System.out.print(bestTour[0]);
        for (int i = 1; i < pointNum; i++) {
            System.out.print("->"+bestTour[i]);
        }
    }
}
