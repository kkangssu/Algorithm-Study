import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken())-1;
        int r = Integer.parseInt(st.nextToken())-1;
        st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken())-1;
        int tr = Integer.parseInt(st.nextToken())-1;

        long[][] dp = new long[r+1][c+1];
        for(int i = 0; i <= r; i++){
            for(int j = 0; j <= c; j++){
                if(i == 0 || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000007;
            }
        }

        System.out.println((dp[tr][tc]*dp[r-tr][c-tc]) % 1000007);
    }
}
