package allen_airplane;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年05月16日 10:33
 */
@Data
public class Ant implements Cloneable{

    private ArrayList<Integer> usedPoint;
    private HashSet<Integer> allowedPoint; // 允许搜索的点
    
    private int[][] distance; // 距离矩阵
    private double[][] delta; // 信息数变化矩阵
    
    private double alpha;
    private double beta;
    
    private int tourLength; // 路径长度
    private int pointNum; // 点数量

    private int firstPoint; // 起始点
    private int currentPoint; // 当前点

    public Ant() {
        pointNum = 30;
        tourLength = 0;

    }

    /**
     * 创建蚂蚁
     * @param num  蚂蚁数量
     */
    public Ant(int num) {
        pointNum = num;
        tourLength = 0;

    }

    /**
     * 初始化蚂蚁，随机选择起始位置
     * @param distance 距离矩阵
     * @param a alpha
     * @param b beta
     */
    public void init(int[][] distance, double a, double b) {
        alpha = a;
        beta = b;
        allowedPoint = new HashSet<>();
        usedPoint = new ArrayList<>();
        this.distance = distance;
        delta = new double[pointNum][pointNum];
        for (int i = 0; i < pointNum; i++) {
            allowedPoint.add(i);
            for (int j = 0; j < pointNum; j++) {
                delta[i][j] = 0.0;
            }
        }

        Random random = new Random(System.currentTimeMillis());
        firstPoint = random.nextInt(pointNum);
        allowedPoint.remove(firstPoint);
        usedPoint.add(firstPoint);
        currentPoint = firstPoint;
    }

    /**
     * 选择写一个点
     * @param pheromone 信息素矩阵
     */
    public void selectNextPoint(double[][] pheromone) {
        double[] p = new double[pointNum];
        double sum = 0.0;
        // 计算分母部分
        for (int i : allowedPoint) {
            sum += Math.pow(pheromone[currentPoint][i], alpha)
                    * Math.pow(1.0 / distance[currentPoint][i], beta);
        }
        // 计算概率矩阵
        for (int i = 0; i < pointNum; i++) {
            if (allowedPoint.contains(i)) p[i] = (Math.pow(pheromone[currentPoint][i], alpha)*Math.pow(1.0/distance[currentPoint][i], beta) / sum);
        }

        // 轮盘赌选择下一个城市
        Random random = new Random(System.currentTimeMillis());
        double sleectP = random.nextDouble();
        int selectCity = 0;
        double sum1 = 0.0;
        for (int i = 0; i < pointNum; i++) {
            sum1 += p[i];
            if (sum1 >= sleectP) {
                selectCity = i;
                break;
            }
        }
        allowedPoint.remove(selectCity);
        usedPoint.add(selectCity);
        currentPoint = selectCity;

    }
    /**
     * 计算路径长度
     * @return 路径长度
     */
    public void calculateTourLength() {
        int len = 0;
        for (int i = 0; i < pointNum-1; i++) {
            len += distance[usedPoint.get(i)][usedPoint.get(i + 1)];
        }
        tourLength=len;
    }
}
