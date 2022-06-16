package graph;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月28日 8:50
 */
public class Floyd {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 3, 1, 5},
                {2, 0, 4, 3},
                {1, 2, 0, 1},
                {0, 1, 5, 0}
        };
        int[][] path = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                path[i][j] = -1;
            }
        }
        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        path[i][j] = k;
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
    }
}
