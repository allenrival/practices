package com.allen3;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年01月08日 21:10
 */
public class Solution {
    protected class CrashException extends RuntimeException {

    }
    public void csase(int a)  {
        if(a>6){
            throw new CrashException();
        }
        System.out.println("aaaaa");
    }
    public  void way(int a){
        csase(a);
        System.out.println("bbbb");
    }
}
