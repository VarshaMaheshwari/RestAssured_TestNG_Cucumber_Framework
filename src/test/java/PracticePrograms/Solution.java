package PracticePrograms;


import java.util.Arrays;

class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int[] finalOp = new int[0];
        for (int i =0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++) {
                if (nums[i] + nums[j] == target) {
                    finalOp = new int[]{nums[i], nums[j]};
                }

            } }


        return finalOp;
    }

    public static void main(String args[]){
        int nums []= {2,7,11,15};
        int target = 9;
        int[] arrSum= twoSum(nums, target);
        System.out.println("arrSum is: "+ Arrays.toString(arrSum));



    }
}