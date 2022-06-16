package allen_airplane;

import java.io.IOException;
import java.util.*;

/**
 * @Description: TODO BFS算法
 * @author: allen
 * @date: 2022年05月09日 16:29
 */
public class BFS {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        ArrayList<Node> pointList = new ArrayList<>();
        // 初始化地图
        int[][][] map = CreateModel.improvedmodel(CreateModel.createModel("src/main/resources/model.txt"), 3).getModel();
        // 遍历map，标记航点
        HashSet<Node> pointSet = new HashSet<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 0; k < map[0][0].length; k++) {
                    if (map[i][j][k] == -1) {
                        pointSet.add(new Node(i, j, k));
                    }
                }
            }
        }
        // 初始化起点
        Node startNode = new Node(110-103, 69-45, 219-198);
        // 记录各点之间的曼哈顿距离
//        int[][] manhattanDistance=new int[pointList.size()][pointList.size()];
//        for(int i=0;i<pointList.size();i++){
//            for(int j=i+1;j<pointList.size();j++){
//                manhattanDistance[i][j]=Math.abs(pointList.get(i).getX()-pointList.get(j).getX())+Math.abs(pointList.get(i).getY()-pointList.get(j).getY())+Math.abs(pointList.get(i).getZ()-pointList.get(j).getZ());
//                manhattanDistance[j][i]=manhattanDistance[i][j];
//            }
//        }
        // 初始化终点
        Deque<Node> queue = new ArrayDeque<>();
        queue.addFirst(startNode);
        HashMap<Node, Node> came_from = new HashMap<>();
        came_from.put(startNode, null);
        Node endNode = null;
        // 开始BFS
        while (!queue.isEmpty()) {
            int length = queue.size();
            while (length-- > 0) {
                Node current = queue.removeLast();
                if (pointSet.contains(current)) {
                    if (pointSet.size() == 1) {
                        endNode = current;
                        pointSet.remove(current);
                        break;
                    }else {
                        pointSet.remove(current);
                    }
                }
                ArrayList<Node> neighbors = neighbors(current, map);
                for (Node neighbor : neighbors) {
                    if (!came_from.containsKey(neighbor)) {
                        queue.addFirst(neighbor);
                        came_from.put(neighbor, current);
                    }
                }
            }
        }
        // 输出路径
        ArrayList<Node> path = new ArrayList<>();
        path.add(endNode);
        while (came_from.get(endNode) != null) {
            endNode = came_from.get(endNode);
            path.add(0, endNode);
        }
        System.out.println("BFS算法找到的路径长度为：" + path.size());
        System.out.println("BFS算法找到的路径为：");
        for (Node node : path) {
            System.out.println(node.getX() + "," + node.getY() + "," + node.getZ());
        }
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
