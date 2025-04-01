import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        
        for(int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);
        int right = 0;
        int left = 0;
        int minDiff = nums[n-1] - nums[0];
        int diff = 0;
        while(left < n && right < n){
            diff = nums[right] - nums[left];
            if(diff < m){
                //System.out.println(left + " " + right + " " + diff);
                right++;
            }
            else{
                minDiff = Math.min(minDiff, diff);
                left++;

                if(left > right) right = left;
            }
        }

        System.out.println(minDiff);
    }
}
