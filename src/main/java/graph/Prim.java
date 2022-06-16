package graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月28日 15:19
 */
public class Prim {
    public static void main(String[] args) {
        int[][] graph = {
                {-1, 28, 0, 10, 0},
                {28, -1, 3, 8, 5},
                {0, 3, -1, 0, 7},
                {10, 8, 0, -1, 9},
                {0, 5, 7, 9, -1}
        };
        //求最小生成树
        List<Integer> list = new ArrayList<>();
        //先将0放置在list中
        list.add(0);
        int begin = 0, end = 0, weight;
        int[] parent = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            parent[i] = -1;
        }
        while (list.size() < graph.length) {
            weight = Integer.MAX_VALUE;
            for (Integer row : list) {
                for (int i = 0; i < graph.length; i++) {
                    if (!list.contains(i)) {
                        if (graph[row][i] > 0 && graph[row][i] < weight) {
                            begin = row;
                            end = i;
                            weight = graph[row][i];
                        }
                    }
                }
            }
            list.add(end);
            parent[end] = begin;
        }
        System.out.println(Arrays.toString(parent));
    }
}
