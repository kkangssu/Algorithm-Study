import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1937_욕심쟁이판다 {

    static int n;
    static int[][] map, dp;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.println(max);
    }

    static int dfs(int r, int c) {
        if(dp[r][c] != 0) return dp[r][c];

        dp[r][c] = 1;
        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(!check(nr, nc)) continue;
            if(map[nr][nc] < map[r][c]) continue;

            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc)+1);
        }
        return dp[r][c];
    }

    static boolean check(int r, int c){
        return r < n && r >= 0 && c < n && c >= 0;
    }
}
