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
        boolean[] paper = new boolean[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            paper[Integer.parseInt(st.nextToken())] = true;
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            if(paper[i]) {
                dp[i] = dp[i-1];
            }

            for (int k = 1; k <= i; k++) {
                int start = i - k + 1;
                int ink = 5 + 2 * k;
                dp[i] = Math.min(dp[i], dp[start-1] + ink);
            }
        }

        System.out.println(dp[n]);
    }
}
