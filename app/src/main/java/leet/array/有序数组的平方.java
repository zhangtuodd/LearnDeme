package leet.array;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/25
 */

class 有序数组的平方 {

    public static void main(String[] args) {
        int[] arr = {-4,-1,0,3,10};
        int[] squares = sortedSquares(arr);
        for (int i:squares){
            System.out.print(i+",");
        }
    }

    public static int[] sortedSquares(int[] arr) {
        int[] nums = new int[arr.length];
        // [-4,-1,0,3,10]
        int left = 0;
        int right = arr.length - 1;
        int newArrIndex = arr.length - 1;
        while(left <= right){
            if(arr[left] * arr[left]  >  arr[right] * arr[right]){
                nums[newArrIndex] = arr[left] * arr[left];
                newArrIndex--;
                left++;
            }else{
                nums[newArrIndex] = arr[right] * arr[right];
                newArrIndex--;
                right--;
            }
        }
        return nums;
    }

}
