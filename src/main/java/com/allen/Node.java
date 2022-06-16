package com.allen;

/**
 * @Description: 航点信息
 * @author: allen
 * @date: 2021年12月27日 14:21
 */
public class Node {
    public int id;
    public double x;
    public double y;
    public double z;

    public Node(int id, double x, double y, double z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getDistance(Node node2) {
        return Math.sqrt(Math.pow(this.x - node2.x, 2) + Math.pow(this.y - node2.y, 2) + Math.pow(this.z - node2.z, 2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getId() {
        return id;
    }
}
