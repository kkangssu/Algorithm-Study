import java.io.*;
import java.util.*;

public class BOJ_11066_파일합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < K; i++)
                files[i] = Integer.parseInt(st.nextToken());

            sb.append(solve(files)).append("\n");
        }
        System.out.println(sb);
    }

    public static int solve(int[] files) {
        int n = files.length;

        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++)
            s[i + 1] = s[i] + files[i];

        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int l = (k == i) ? 0 : dp[i][k];
                    int r = (k + 1 == j) ? 0 : dp[k + 1][j];
                    int c = s[j + 1] - s[i];

                    dp[i][j] = Math.min(dp[i][j], l + r + c);
                }
            }
        }

        return dp[0][n - 1];
    }
}