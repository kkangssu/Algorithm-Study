import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int ans = 1;
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        
        int exit = routes[0][1];
        for(int[] route : routes) {
            if(exit < route[0]) {
                ans++;
                exit = route[1];
            }
        }
        
        return ans;
    }
}
