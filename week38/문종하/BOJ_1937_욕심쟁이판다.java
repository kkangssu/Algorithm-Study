import java.io.*;
import java.util.*;

/*
    n × n의 크기의 대나무 숲
    옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 함

    n(1 ≤ n ≤ 500)
    대나무의 양은 1,000,000보다 작거나 같은 자연수
*/
public class BOJ_1937_욕심쟁이판다 {
    static int n, ans;
    static int[][] forest;
    static int[][] dp;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        forest = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                forest[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                ans = Math.max(ans, dfs(i, j));

        System.out.println(ans);
    }

    static int dfs(int y, int x) {
        if (dp[y][x] != 0)
            return dp[y][x];

        dp[y][x] = 1;

        for (int d = 0; d < 4; d++) {
            int yy = y + dy[d];
            int xx = x + dx[d];

            if (check(yy, xx) && forest[yy][xx] > forest[y][x])
                dp[y][x] = Math.max(dp[y][x], dfs(yy, xx) + 1);
        }

        return dp[y][x];
    }

    static boolean check(int yy, int xx) {
        return yy >= 0 && yy < n && xx >= 0 && xx < n;
    }
}