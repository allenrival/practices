package com.allen;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 蚁群算法匹配航点
 * @author: allen
 * @date: 2021年12月27日 14:20
 */
public class Main {
    private  static Logger logger=Logger.getLogger("Main.class");
    private  static int iteratorNum=1000;  //迭代次数1000次
    private  static int antNum=10;   //蚂蚁数量
    private  static  double p=0.5; //每完成一次迭代后，信息素衰减的比例
    private  static  double q=2;  //蚂蚁每次经过一条路径，信息素增加的比例
    private  static double[][] distance;   //距离矩阵
    private  static double[][] pheromoneMatrix;   //信息素矩阵
    private  static  int[] maxPheromoneMatrix;   //每行最大信息素下标
    private  static  int[] criticalPointMatrix;   //在一次迭代中，采用随机分配策略的蚂蚁的临界编号
    private  static Node[] framenodes;  //框架航点
    private  static Node[] testnodes;   //测试航点
    private  static double[][] resultData; //存储每次迭代蚂蚁的最大距离
    private  static int[][][] resnodeId;
    public static void main(String[] args) throws IOException {
        logger.info("输入配准框架");
        initFrameArray("c:/Users/Administrator/Desktop/麂友一回#002.kml");
        logger.info("输入测试航点");
        initTestArray("c:/Users/Administrator/Desktop/麂友一回#007.kml");
        //打乱测试航点坐标中得顺序
        shuffle(testnodes);
        //初始化距离矩阵
        logger.info("初始化距离矩阵");
        initDistanceMatrix(framenodes,testnodes);
        //初始化信息素矩阵
        logger.info("初始化信息素矩阵");
        initPheromoneMatrix(framenodes.length,testnodes.length);
        logger.info("开始迭代搜素");
        acaSearch(iteratorNum,antNum);
    }

    /**
     * 打乱数组顺序
     * @param a 数组
     */
    private static void shuffle(Node[] a) {
        for (int i = 0; i < a.length-1; i++) {
            int random = (int)(Math.random()*(a.length-i));
            Node node = a[a.length-1-i];
            a[a.length-1-i] = a[random];
            a[random] = node;
        }
    }

    /**
     * 迭代搜素
     * @param iteratorNum 迭代次数
     * @param antNum 蚂蚁数量
     */
    private static void acaSearch(int iteratorNum, int antNum) {
        resultData=new double[iteratorNum][antNum];
        resnodeId=new int[iteratorNum][antNum][testnodes.length];
        for(int i=0;i<iteratorNum;i++){
            logger.info("第"+i+"次迭代");
            int[][][] pathMatrixAllAnt=new int[antNum][framenodes.length][testnodes.length]; //储存一次迭代过程中所有蚂蚁的路径
            for(int antCount=0;antCount<antNum;antCount++) {
                logger.info("第"+antCount+"只蚂蚁的选择");
                int[][] pathMatrixOneAnt = new int[framenodes.length][testnodes.length]; //存储一只蚂蚁的分配策略
                for (int framenode = 0; framenode< framenodes.length; framenode++) {
                    int testnode = assignOneTask(antCount, framenode, testnodes, pheromoneMatrix);
                    pathMatrixOneAnt[framenode][testnode] = 1;
                    System.out.print(testnodes[testnode].getId()+" ");
                }
                System.out.println();
                pathMatrixAllAnt[antCount] = pathMatrixOneAnt;
            }
            double[] distanceArrayOneIt=callTime(pathMatrixAllAnt);
            resultData[i]=distanceArrayOneIt;
            updataPheromoneMatrix(pathMatrixAllAnt,pheromoneMatrix,distanceArrayOneIt);
        }
    }

    /**
     * 更新信息素
     * @param pathMatrixAllAnt 所有蚂蚁的爬行路径
     * @param pheromoneMatrix  信息素矩阵
     * @param distanceArrayOneIt 一次迭代的最大距离
     */
    private static void updataPheromoneMatrix(int[][][] pathMatrixAllAnt, double[][] pheromoneMatrix, double[] distanceArrayOneIt) {
        //信息素递减
        for(int i=0;i<framenodes.length;i++){
            for(int j=0;j<testnodes.length;j++){
                pheromoneMatrix[i][j]*=p;
            }
        }
        double minLength=Integer.MAX_VALUE;
        int minIndex=-1;
        for(int antIndex=0;antIndex<antNum;antIndex++){
            if(distanceArrayOneIt[antIndex]<minLength){
                minLength=distanceArrayOneIt[antIndex];
                minIndex=antIndex;
            }
        }
        for(int frameNodeIndex=0;frameNodeIndex<framenodes.length;frameNodeIndex++){
            for(int testNodeIndex=0;testNodeIndex<testnodes.length;testNodeIndex++){
                if(pathMatrixAllAnt[minIndex][frameNodeIndex][testNodeIndex]==1){
                    pheromoneMatrix[frameNodeIndex][testNodeIndex]*=q;
                }
            }
        }
        for(int frameNodeIndex=0;frameNodeIndex<framenodes.length;frameNodeIndex++){
            double maxPheromone=pheromoneMatrix[frameNodeIndex][0];
            int maxIndex=0;
            double sumPheromone=pheromoneMatrix[frameNodeIndex][0];
            boolean isAllSame=true;
            for(int testNodeIndex=1;testNodeIndex<testnodes.length;testNodeIndex++){
                if(pheromoneMatrix[frameNodeIndex][testNodeIndex]>maxPheromone){
                    maxPheromone=pheromoneMatrix[frameNodeIndex][testNodeIndex];
                }
                if(pheromoneMatrix[frameNodeIndex][testNodeIndex]!=pheromoneMatrix[frameNodeIndex][testNodeIndex-1]){
                    isAllSame=false;
                }
                sumPheromone+=pheromoneMatrix[frameNodeIndex][testNodeIndex];
            }
            if(isAllSame==true){
                maxIndex=(int) (Math.random()*(testnodes.length-1));
                maxPheromone=pheromoneMatrix[frameNodeIndex][maxIndex];
            }
            maxPheromoneMatrix[frameNodeIndex]=maxIndex;
            criticalPointMatrix[frameNodeIndex]=(int) Math.round(antNum*(maxPheromone/sumPheromone));
        }
    }

    /**
     * 计算一次迭代过程中所有蚂蚁的连接最大距离
     * @param pathMatrixAllAnt 所有蚂蚁的爬行路径
     * @return
     */
    private static double[] callTime(int[][][] pathMatrixAllAnt) {
        double[] dictanceAllAnt=new double[pathMatrixAllAnt.length];
        for(int antIndex=0;antIndex<pathMatrixAllAnt.length;antIndex++){
            int[][] pathMatrix=pathMatrixAllAnt[antIndex];
            int maxlength=-1;
            for(int framernodeIndex=0;framernodeIndex<framenodes.length;framernodeIndex++){
                int length=0;
                for(int testnodeIndex=0;testnodeIndex<testnodes.length;testnodeIndex++){
                    if(pathMatrix[framernodeIndex][testnodeIndex]==1){
                        length+=distance[framernodeIndex][testnodeIndex];
                    }
                }
                maxlength=Math.max(maxlength,length);
            }
            dictanceAllAnt[antIndex]=maxlength;
        }
        return dictanceAllAnt;
    }

    /**
     *
     * @param antCount  蚂蚁编号
     * @param framenode  框架航点
     * @param testnodes   测试航点集合
     * @param pheromoneMatrix  信息素集合
     * @return
     */
    private static int assignOneTask(int antCount, int framenode, Node[] testnodes, double[][] pheromoneMatrix) {
        if(antCount<=criticalPointMatrix[framenode]){
            return maxPheromoneMatrix[framenode];
        }
        else return (int) (Math.random()*(testnodes.length-1));
    }

    /**
     * 初始化信息素矩阵
     * @param size 框架航点数量
     * @param size1 测试航点数量
     * @return
     */
    private static void initPheromoneMatrix(int size, int size1) {
        pheromoneMatrix=new double[size][size1];
        for(int i=0;i<size;i++){
            Arrays.fill(pheromoneMatrix[i],1.0);
        }
    }

    /**
     * 矩阵储存框架航点和测试航点之间得距离
     * @param framenodes  框架航点
     * @param testnodes 测试航点
     */
    private static void initDistanceMatrix(Node[] framenodes, Node[] testnodes) {
        distance=new double[framenodes.length][testnodes.length];
        for(int i=0;i<framenodes.length;i++){
            for(int j=0;j<testnodes.length;j++){
                double x1=framenodes[i].getX();
                double y1=framenodes[i].getY();
                double z1=framenodes[i].getZ();
                double x2=testnodes[j].getX();
                double y2=testnodes[j].getY();
                double z2=testnodes[j].getZ();
                distance[i][j]=Math.pow(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)+Math.pow(z1-z2,2),0.5);
                System.out.println(distance[i][j]);
            }
        }
    }

    /**
     * 输入用于匹配的框架航点坐标
     * @param framepath  kml文件名称，从kml文件中读取框架航点信息
     */
    public static void initFrameArray(String framepath) throws IOException {
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
        framenodes=new Node[eachpoint.length];
        for(int i=0;i<eachpoint.length;i++){
            String[] xyz=eachpoint[i].split(",");
            Node node=new Node(i,Double.parseDouble(xyz[0]),Double.parseDouble(xyz[1]),Double.parseDouble(xyz[2]));
            framenodes[i]=node;
        }
        maxPheromoneMatrix =new int[eachpoint.length];
        criticalPointMatrix=new int[eachpoint.length];
    }

    /**
     * 读取根据绝缘子串坐标计算出来的航点，将航点坐标放入列表。
     * @param testpath 测试航点
     */
    public static void initTestArray(String testpath) throws IOException {
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
        testnodes=new Node[eachpoint.length];
        for(int i=0;i<eachpoint.length;i++) {
            String[] xyz = eachpoint[i].split(",");
            Node node = new Node(i, Double.parseDouble(xyz[0]), Double.parseDouble(xyz[1]), Double.parseDouble(xyz[2]));
            testnodes[i] = node;
        }
    }
}
