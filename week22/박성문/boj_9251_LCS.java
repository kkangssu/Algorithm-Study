package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	static char[] map1;
	static char[] map2;
	static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        map1 = br.readLine().toCharArray();
        map2 = br.readLine().toCharArray();

        int N = map1.length;
        int M = map2.length;
        
        int answer = 0;

        dp = new int[N+1][M+1];
        for(int i = 1; i <= N; i++) {
        	for(int j = 1; j <= M; j++) {
        		if(map1[i-1] != map2[j-1]) {
        			dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
        		} else {
        			dp[i][j] = dp[i-1][j-1]+1;
        		}
        	}
        }

     //   System.out.println(Arrays.deepToString(dp));
        
        System.out.println(dp[N][M]);

            
    }
	
}
