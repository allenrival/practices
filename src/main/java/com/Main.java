package com;



import com.allen.Node;

import java.sql.SQLOutput;
import java.util.*;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月05日 9:57
 */
public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1, 531923.4688391193, 3399903.7631239323, 110.71135940560146);
        Node node2 = new Node(2, 531927.5675330186, 3399895.1914431658, 97.8014153978502);
        double disx = node1.getX() - node2.getX();
        double disy = node1.getY() - node2.getY();
        double pdis = Math.sqrt(disx * disx + disy * disy);
        double distance = node1.getDistance(node2);
        double angle = Math.toDegrees(Math.acos(pdis / distance));
        System.out.println(angle);
    }
}

