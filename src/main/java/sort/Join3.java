package sort;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月03日 16:24
 */
public class Join3 {
    public static void main(String[] args) {
        int[] nums=new int[]{5,9,7,8,6,2,3,10};
        for(int i = nums.length/2-1; i>=0; i--){
            adjustheap(nums,i,nums.length);
        }
        for(int i=nums.length-1;i>=1;i--){
            swap(nums,0,i);
            adjustheap(nums,0,i);
        }
        for(int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }
    public static void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    public static void adjustheap(int[] nums,int i,int length){
        int temp=nums[i];
        for(int k=2*i+1;k<length;k=2*k+1){
            if(k+1<length&&nums[k]<nums[k+1]){
                k++;
            }
            if(nums[k]>temp){
                nums[i]=nums[k];
                i=k;
            }else break;
        }
        nums[i]=temp;
    }
}
