import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, ans;
    static int[][] forest, dp;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ans = 0;
        forest = new int[N][N];
        dp = new int[N][N];
        for(int i=0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j < N; j++)
                forest[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i < N; i++) {
            for(int j=0; j < N; j++) {
                int cur = eat(i, j);
                ans = Math.max(ans, cur);
            }
        }

        System.out.println(ans);
    }

    static int eat(int y, int x) {
        if(dp[y][x] > 0)
            return dp[y][x];

        dp[y][x] = 1;

        for(int i=0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!check(ny, nx) || (forest[ny][nx] <= forest[y][x]))
                continue;

            int next = eat(ny, nx) + 1;
            dp[y][x] = Math.max(dp[y][x], next);
        }

        return dp[y][x];
    }

    static boolean check(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}
