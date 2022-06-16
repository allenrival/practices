package graph.pratice;

import java.io.*;
import java.util.Scanner;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月29日 15:23
 */
public class Main01Dijkstra {
    public static void main(String[] args) throws IOException {
        int[][] graph=new int[][]{
                {0,4,6,6,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,0,1,Integer.MAX_VALUE,7,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,0,Integer.MAX_VALUE,6,4,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,2,0,Integer.MAX_VALUE,5,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0,Integer.MAX_VALUE,6},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,8},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0},
        };
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        int start=Integer.parseInt(bufferedReader.readLine());
        int end=Integer.parseInt(bufferedReader.readLine());
        int[] distance=new int[graph.length];
        int[] path=new int[graph.length];
        int[] visited=new int[graph.length];
        for(int i=0;i<graph.length;i++){
            distance[i]=Integer.MAX_VALUE;
            path[i]=-1;
        }
        for(int i=0;i<graph[start].length;i++){
            if(graph[start][i]!=Integer.MAX_VALUE) {
                distance[i] = graph[start][i];
                path[i]=start;
            }
        }
        visited[start]=1;
        for(int i=0;i<graph.length-1;i++){
            int min=0;
            int dis=Integer.MAX_VALUE;
            for(int j=0;j<graph.length;j++){
                if(visited[j]==0&&distance[j]<dis){
                    min=j;
                    dis=distance[j];
                }
            }
            for(int j=0;j<graph[min].length;j++){
                if(graph[min][j]!=Integer.MAX_VALUE&&distance[min]!=Integer.MAX_VALUE&&distance[j]>graph[min][j]+distance[min]){
                    distance[j]=graph[min][j]+distance[min];
                    path[j]=min;
                }
            }
            visited[min]=1;
        }
        for(int i=0;i<graph.length;i++){
            System.out.println(distance[i]);
        }
        for(int i=0;i<graph.length;i++){
            System.out.println(path[i]);
        }

    }
}
