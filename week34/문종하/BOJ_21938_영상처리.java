import java.io.*;
import java.util.*;

public class BOJ_21938_영상처리 {
    static int[][] display;
    static boolean[][] v;
    static int T, N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        display = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                double sum = 0;
                for (int k = 0; k < 3; k++)
                    sum += Integer.parseInt(st.nextToken());

                display[i][j] = (int) Math.floor(sum / 3);
            }
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        int cnt = 0;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (dfs(i, j)) cnt++;

        System.out.print(cnt);
    }

    public static boolean dfs(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M || display[x][y] < T || v[x][y])
            return false;

        if (display[x][y] >= T) {
            v[x][y] = true;
            dfs(x + 1, y);
            dfs(x - 1, y);
            dfs(x, y + 1);
            dfs(x, y - 1);
            return true;
        }

        return false;
    }
}