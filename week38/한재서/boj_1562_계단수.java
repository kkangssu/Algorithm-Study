import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static final int MOD = 1000000000;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[][][] dp = new long[N + 1][10][1 << 10];
        long ans = 0;

        for(int i=1; i < 10; i++)
            dp[1][i][1 << i] = 1;

        for(int i=2; i < N + 1; i++) {
            for(int j=0; j < 10; j++) {
                for(int k=0; k < (1 << 10); k++) {
                    int cur = k | (1 << j);
                    if(j == 0)
                        dp[i][j][cur] += dp[i - 1][j + 1][k] % MOD;
                    else if(j == 9)
                        dp[i][j][cur] += dp[i - 1][j - 1][k] % MOD;
                    else
                        dp[i][j][cur] += (dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % MOD;

                    dp[i][j][cur] %= MOD;
                }
            }
        }

        for(int i=0; i < 10; i++) {
            ans += dp[N][i][(1 << 10) - 1] % MOD;
            ans %= MOD;
        }

        System.out.println(ans);
    }
}
