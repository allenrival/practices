package sort;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月03日 16:15
 */
public class Join2 {
    public static void main(String[] args) {
        int[] nums=new int[]{5,9,7,8,6,2,3,10};
        quickSort(nums,0,nums.length-1);
        for(int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }
    public static void quickSort(int[] nums,int left,int right){
        if(left>=right) return;
        int temp=nums[left];
        int i=left,j=right;
        while(i<j){
            while(i<j&&nums[j]>=temp){
                j--;
            }
            if(i<j) {
                nums[i]=nums[j];
                i++;
            }
            while(i<j&&nums[i]<=temp){
                i++;
            }
            if(i<j){
                nums[j]=nums[i];
                j--;
            }
        }
        nums[i]=temp;
        quickSort(nums,left,i);
        quickSort(nums,i+1,right);
    }
}
