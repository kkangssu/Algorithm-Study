import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        int m = 2;
        for(int T = 0; T < t; T++){
            int n = Integer.parseInt(br.readLine());
            if(n > m){
                for(int i = m; i <= n; i++){
                    if(i%2 == 0){
                        dp[i] = dp[i-1] + dp[i/2];
                    }
                    else{
                        dp[i] = dp[i-1];
                    }
                }
                m = n;
            }
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
