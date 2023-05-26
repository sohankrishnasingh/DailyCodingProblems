import java.util.Arrays;

/**
 * Problem: Minimum number of jumps to reach end.
 * Approach: Method1-DynamicProgramming or Method2-BFS
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * @author Sohan Krishna Singh
 * @since 2019-12-27
 */
public class MinJumpsToReachEnd {

    public static void main(String[] args) {
        int[] arr = { 6, 2, 4, 0, 5, 1, 1, 4, 2, 9 };
        int size = arr.length;
        System.out.println("Minimum number of jumps to reach end in " + Arrays.toString(arr) + " is: " + minJumps(arr, size)); // Using DP
        System.out.println("Minimum number of jumps to reach end in " + Arrays.toString(arr) + " is: " + minJumpsToReachEnd(arr, size)); // Using BFS
    }

    /* Dynamic Programming Approach.
     * Approach: Build jumps[] from right to left. jumps[i] gives min # of jumps needed to reach arr[n-1] from arr[i]. Finally, we return arr[0]
     * Runtime: 0ms, Memory: 36.3MB
     */
    private static int minJumps(int arr[], int n)
    {
        int[] jumps = new int[n];  // jumps[0] will hold the result
        jumps[n - 1] = 0; // Minimum number of jumps needed to reach last element from last elements itself is always 0
        int min;

        // jumps[i] represents min number of jumps needed to reach arr[m-1] from arr[i]
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] == 0) {  // If arr[i] is 0 then arr[n-1] can't be reached from here
                jumps[i] = Integer.MAX_VALUE;
            }
            else if (arr[i] >= n - i - 1) {  // If we can directly reach to the end point from here then jumps[i] is 1
                jumps[i] = 1;
            }
            else {  // Otherwise, to find out the minimum number of jumps needed to reach arr[n-1], check all the points reachable from here and jumps[] value for those points
                min = Integer.MAX_VALUE;  // initialize min value
                for (int j = i + 1; j < n && j <= arr[i] + i; j++) { // check with all reachable points and takes the minimum
                    if (min > jumps[j]) {
                        min = jumps[j];
                    }
                }
                if (min != Integer.MAX_VALUE) {  // Handle overflow
                    jumps[i] = min + 1;
                }
                else {
                    jumps[i] = min; // or Integer.MAX_VALUE
                }
            }
        }
        return jumps[0];
    }

    /* BFS Approach
     * Approach: Try to find the “range” which represents all the nodes can be reached in every single jump.
     *    We first define the parameter “level” to stand for the BFS level of all the jumps.
     *    Every time when we are trying to make a jump, we go through all the nodes in nums[i:preStep] and try to expand the BFS range,
             where “i” is the current position and “preStep” is the range generated by the previous jump.
     *    Then, we store the range in the defined parameter “curStep” and update “preStep” before we go traverse all the nodes.
     */
    // Runtime: 1ms, Memory: 41.3MB
    private static int minJumpsToReachEnd(int[] nums, int n) {
        int curStep = 0, i = 0, level = 0;
        while(curStep < n-1) {
            level++; //BFS level
            int preStep = curStep; //update preStep
            for(;i <= preStep;i++) { //traverse of preStep level & find max step of this level
                curStep = Math.max(curStep, i+nums[i]);
            }
        }
        return level;
    }

}


/*
Output:
——————————————————— 
2
2

*/
