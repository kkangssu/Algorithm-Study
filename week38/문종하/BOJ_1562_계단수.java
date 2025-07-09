import java.io.*;
import java.util.*;

/*
    인접한 모든 자리의 차이가 1 => 계단수
*/
public class BOJ_1562_계단수 {
    static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][][] dp = new int[N + 1][10][1024];

        for (int i = 1; i <= 9; i++)
            dp[1][i][1 << i] = 1;

        for (int len = 2; len <= N; len++)
            for (int digit = 0; digit <= 9; digit++)
                for (int mask = 0; mask < 1024; mask++) {
                    if (dp[len - 1][digit][mask] == 0) continue;

                    if (digit > 0) {
                        int newMask = mask | (1 << (digit - 1));
                        dp[len][digit - 1][newMask] = (dp[len][digit - 1][newMask] + dp[len - 1][digit][mask]) % MOD;
                    }

                    if (digit < 9) {
                        int newMask = mask | (1 << (digit + 1));
                        dp[len][digit + 1][newMask] = (dp[len][digit + 1][newMask] + dp[len - 1][digit][mask]) % MOD;
                    }
                }

        int res = 0;
        for (int digit = 0; digit <= 9; digit++)
            res = (res + dp[N][digit][1023]) % MOD;

        System.out.println(res);
    }
}