package graph.pratice;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月29日 16:26
 */
public class Main01Floyd {
    public static void main(String[] args) {
        int[][] graph=new int[][]{
                {0,4,6,6,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,0,1,Integer.MAX_VALUE,7,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,0,Integer.MAX_VALUE,6,4,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,2,0,Integer.MAX_VALUE,5,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0,Integer.MAX_VALUE,6},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,8},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0},
        };
        int[][] path=new int[graph.length][graph.length];
        for(int i=0;i<graph.length;i++){
            for(int j=0;j< graph.length;j++){
                if(graph[i][j]==Integer.MAX_VALUE) path[i][j]=-1;
                else path[i][j]=i;
            }
        }
        for(int k=0;k< graph.length;k++){
            for(int i=0;i<graph.length;i++){
                for(int j=0;j< graph.length;j++){
                    if(graph[i][k]!=Integer.MAX_VALUE&&graph[k][j]!=Integer.MAX_VALUE&&graph[i][k]+graph[k][j]< graph[i][j]){
                        graph[i][j]=graph[i][k]+graph[k][j];
                        path[i][j]=path[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
    }
}
