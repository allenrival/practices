package graph;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月28日 10:27
 */
public class MainDijkstra {
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
        int start=0;
        int end=6;
        int[] distance=new int[graph.length];
        Arrays.fill(distance,Integer.MAX_VALUE);
        int[] path=new int[graph.length];
        int[] visited=new int[graph.length];
        distance[start]=0;
        for(int i=0;i<graph.length-1;i++){
            int index=-1;
            int min=Integer.MAX_VALUE;
            for(int j=0;j< graph.length;j++){
                if(visited[j]==0&&distance[j]<min){
                    min=distance[j];
                    index=j;
                }
            }
            visited[index]=1;
            for(int j=0;j<graph.length;j++){
                if(visited[j]==0&&graph[index][j]!=Integer.MAX_VALUE&&distance[index]+graph[index][j]<distance[j]){
                    distance[j]=distance[index]+graph[index][j];
                    path[j]=index;
                }
            }
        }
        System.out.println(distance[end]);
        int temp = end;
        while (temp != start) {
            System.out.println(temp);
            temp = path[temp];
        }
        System.out.println(start);
    }
}
