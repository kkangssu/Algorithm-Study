package week32;

import java.util.*;
import java.io.*;

public class boj_27446_랩실에서잘자요 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        boolean[] hasPage = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int pageNum = Integer.parseInt(st.nextToken());
            hasPage[pageNum] = true;
        }
        
        boolean hasAllPages = true;
        for(int i = 1; i <= N; i++) {
            if(!hasPage[i]) {
                hasAllPages = false;
                break;
            }
        }
        
        if(hasAllPages) {
            System.out.println(0);
            return;
        }
        
        int[] dp = new int[N+1];
        Arrays.fill(dp, 101);
        dp[0] = 0;
        
        for(int i = 1; i <= N; i++) {
            if(hasPage[i]) {
                dp[i] = dp[i-1];
            } else {
                dp[i] = dp[i-1] + 7;
            }
            
            for(int j = 1; j <= i; j++) {
                int printingPages = i - j + 1;
                int inkCost = 5 + 2 * printingPages;
                
                dp[i] = Math.min(dp[i], dp[j-1] + inkCost);
            }
        }
        
        System.out.println(dp[N]);
    }
}