package sort;

import java.util.PriorityQueue;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年02月17日 17:17
 */
public class Join {
    public static void main(String[] args) {
        int[] nums=new int[]{5};
        mergeSort(nums,0,nums.length-1);
        for(int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }
    public static void mergeSort(int[] nums,int left,int right){
        if(left>=right) return;
        int mid=left+(right-left)/2;
        mergeSort(nums,left,mid);
        mergeSort(nums,mid+1,right);
        sort(nums,left,mid,right);
    }
    public static void sort(int[] nums,int left,int mid,int right) {
        int[] temp=new int[right-left+1];
        int w=0;
        int i=left,j=mid+1;

        while(i<=mid&&j<=right){
            if(nums[i]<nums[j]){
                temp[w++]=nums[i++];
            }else temp[w++]=nums[j++];
        }
        if(i>mid){
            while(w<right-left+1){
                temp[w++]=nums[j++];
            }
        }else{
            while(w<right-left+1){
                temp[w++]=nums[i++];
            }
        }
        for(int k=0;k<w;k++){
            nums[left+k]=temp[k];
        }
    }
}
