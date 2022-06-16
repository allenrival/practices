package graph;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月29日 8:27
 */
public class Kruskal {
    public static void main(String[] args) {
        int[][] edges = {{0, 1, 2}, {0, 2, 5}, {1, 2, 3}, {1, 3, 6}, {2, 3, 1}};
        int n = 4;
        int m = 5;
        int[] parent = new int[n];
        int[] rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        int[] result = new int[m];
        int index = 0;
        for(int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            int pu = find(u, parent);
            int pv = find(v, parent);
            if(pu != pv) {
                result[index++] = w;
                union(pu, pv, parent, rank);
            }
        }
        for(int i = 0; i < index; i++) {
            System.out.println(result[i]);
        }
    }

    private static void union(int pu, int pv, int[] parent, int[] rank) {
        if(rank[pu] > rank[pv]) {
            parent[pv] = pu;
        } else if(rank[pu] < rank[pv]) {
            parent[pu] = pv;
        } else {
            parent[pv] = pu;
            rank[pu]++;
        }
    }

    private static int find(int u, int[] parent) {
        while(u != parent[u]) {
            u = parent[u];
        }
        return u;
    }
}
