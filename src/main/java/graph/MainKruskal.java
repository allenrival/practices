package graph;

import java.util.Arrays;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月29日 14:27
 */
public class MainKruskal {
    public static void main(String[] args) {
        int[][] edges = {{0, 1, 2}, {0, 2, 5}, {1, 2, 3}, {1, 3, 6}, {2, 3, 1}};
        int n = 4;
        int m = 5;
        int[] parent=new int[n];
        int[] rank=new int[n];
        int[] res=new int[m];
        int index=0;
        for(int i=0;i<n;i++) {
            parent[i] = i;
        }
        Arrays.sort(edges,(int[] o1,int[]o2)->o1[2]-o2[2]);
        for(int[]edge :edges){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];
            int pu=find(u,parent);
            int pv=find(v,parent);
            if(pu!=pv){
                res[index++]=w;
                union(pu,pv,rank,parent);
            }
        }
        for(int i=0;i<index;i++){
            System.out.println(res[i]);
        }
    }
    public static int find(int k,int[] parent){
        if(k!=parent[k]){
            k=parent[k];
        }
        return k;
    }
    public static void union(int pu,int pv,int[] rank,int[] parent){
        if(rank[pu]>rank[pv]){
            parent[pv]=pu;
        }else if(rank[pu]<rank[pv]){
            parent[pu]=pv;
        }else{
            parent[pu]=pv;
            rank[pv]++;
        }
    }
}
