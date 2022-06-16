package graph;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月28日 8:59
 */
public class Dijkstra {
    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 3, 1, 5},
                {2, 0, 4, 3},
                {1, 2, 0, 1},
                {8, 1, 5, 0}
        };
        int source = 0;
        int target = 3;
        int[] distance = new int[graph.length];
        int[] path = new int[graph.length];
        int[] visited = new int[graph.length];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        distance[source] = 0;
        for (int i = 0; i < graph.length - 1; i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int j = 0; j < graph.length; j++) {
                if (visited[j] == 0 && distance[j] < min) {
                    min = distance[j];
                    index = j;
                }
            }
            visited[index] = 1;
            for (int j = 0; j < graph.length; j++) {
                if (visited[j] == 0 && graph[index][j] != 0 && distance[index] != Integer.MAX_VALUE && distance[index] + graph[index][j] < distance[j]) {
                    distance[j] = distance[index] + graph[index][j];
                    path[j] = index;
                }
            }
        }
        System.out.println("distance:" + distance[target]);
        int temp = target;
        while (temp != source) {
            System.out.println(temp);
            temp = path[temp];
        }
        System.out.println(source);
    }
}
