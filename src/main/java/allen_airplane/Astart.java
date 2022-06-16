package allen_airplane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @Description: TODO A*算法
 * @author: allen
 * @date: 2022年05月10日 8:29
 */
public class Astart {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        // 初始化地图
        int[][][] map = CreateModel.improvedmodel(CreateModel.createModel("src/main/resources/model.txt"), 3).getModel();
        ArrayList<Node> pointList = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 0; k < map[0][0].length; k++) {
                    if (map[i][j][k] == -1) {
                        pointList.add(new Node(i, j, k));
                    }
                }
            }
        }
        int[][] distance=new int[pointList.size()][pointList.size()];  //任意两点间实际距离
        ArrayList<Node>[][] path=new ArrayList[pointList.size()][pointList.size()]; //任意两点间实际路径
        for(int i=0;i<pointList.size();i++){
            Node start=pointList.get(i);
            for(int j=i+1;j<pointList.size();j++){
                Node end=pointList.get(j);
                HashMap<Node, Node> came_from = new HashMap<>();
                HashMap<Node, Integer> cost_so_far = new HashMap<>();
                HashMap<Node, Integer> heuristic = new HashMap<>();
                PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> (int) (heuristic.get(o1) - heuristic.get(o2)));
                queue.add(start);
                came_from.put(start, null);
                cost_so_far.put(start, 0);
                while (!queue.isEmpty()) {
                    Node current = queue.poll();
                    if (current.equals(end)) {
                        break;
                    }
                    for (Node neighbor : neighbors(current, map)) {
                        int new_cost = cost_so_far.get(current) + 1;
                        if (!cost_so_far.containsKey(neighbor) || new_cost < cost_so_far.get(neighbor)){
                            cost_so_far.put(neighbor, new_cost);
                            int priority = new_cost + Math.abs(end.getX() - neighbor.getX()) + Math.abs(end.getY() - neighbor.getY()) + Math.abs(end.getZ() - neighbor.getZ());
                            heuristic.put(neighbor, priority);
                            queue.add(neighbor);
                            came_from.put(neighbor, current);
                        }
                    }
                }
                ArrayList<Node> patha = new ArrayList<>();
                patha.add(end);
                distance[i][j]=cost_so_far.get(end);
                while (came_from.get(end) != null) {
                    end = came_from.get(end);
                    patha.add(end);
                }
                Collections.reverse(patha);
                path[i][j]=patha;
            }
        }
        //输出计算结果
        //文件记录输出结果
        BufferedWriter bw=new BufferedWriter(new FileWriter("src/main/resources/result.txt"));
        bw.write(pointList.size()+"\n");
        for(int i=0;i<pointList.size();i++){
            for(int j=i+1;j<pointList.size();j++){
                bw.write(i+" "+j+" "+distance[i][j]+"\n");
                bw.write("path length:"+path[i][j].size()+"\n");
                for(Node node:path[i][j]){
                    bw.write(node.getX()+" "+node.getY()+" "+node.getZ()+"\n");
                }
            }
        }
        bw.close();
        System.out.println("路径规划完毕，耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static ArrayList<Node> neighbors(Node current, int[][][] map) {
        ArrayList<Node> neighbors = new ArrayList<>();
        int x = current.getX();
        int y = current.getY();
        int z = current.getZ();
        if (x - 1 >= 0&&map[x-1][y][z]!=1) {
            neighbors.add(new Node(x - 1, y, z));
        }
        if (x + 1 <map.length&&map[x+1][y][z]!=1) {
            neighbors.add(new Node(x + 1, y, z));
        }
        if (y - 1 >= 0&&map[x][y-1][z]!=1) {
            neighbors.add(new Node(x, y - 1, z));
        }
        if (y + 1 < map[0].length&&map[x][y+1][z]!=1) {
            neighbors.add(new Node(x, y + 1, z));
        }
        if (z - 1 >= 0&&map[x][y][z-1]!=1) {
            neighbors.add(new Node(x, y, z - 1));
        }
        if (z + 1 < map[0][0].length&&map[x][y][z+1]!=1) {
            neighbors.add(new Node(x, y, z + 1));
        }
        return neighbors;
    }
}
