package graph;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月28日 13:34
 */
public class MainFloyd {
    public static void main(String[] args) {
        int[][] matrix=new int[][]{
                {0,4,6,6,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,0,1,Integer.MAX_VALUE,7,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,0,Integer.MAX_VALUE,6,4,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,2,0,Integer.MAX_VALUE,5,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0,Integer.MAX_VALUE,6},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,8},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0},
        };
        int[][] path = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j]==Integer.MAX_VALUE) path[i][j] = -1;
                else path[i][j] = i;
            }
        }
        for (int k = 0; k<matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][k]!=Integer.MAX_VALUE&&matrix[k][j]!=Integer.MAX_VALUE&&matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("从0到6的最短路径是：");
        System.out.println(0);
        getPath(0,6,path);
        System.out.println(6);
    }
    static void getPath(int start, int end, int path[][]){
        if(path[start][end] == start){
            return;
        }
        getPath(start,path[start][end],path);
        System.out.println(path[start][end]);
    }
}
