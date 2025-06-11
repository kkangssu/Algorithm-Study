import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];
        
        for(int i=3; i < 1001; i++)
            dp[i] = -1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i < 1001; i++) {
            if(i % 2 == 0)
                dp[i] = dp[i - 1] + dp[i / 2];
            else
                dp[i] = dp[i - 1];
        }
        
        for(int t=0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
