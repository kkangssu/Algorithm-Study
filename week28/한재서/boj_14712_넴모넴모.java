import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, ans;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        ans = 0;

        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int depth, int start) {
        ans += check(depth) ? 1 : 0;
        if(depth == N * M) return;

        for(int i=start; i < N * M; i++) {
            int y = i / M;
            int x = i % M;
            if(visited[y][x]) continue;
            visited[y][x] = true;
            dfs(depth + 1, i + 1);
            visited[y][x] = false;
        }
    }

    static boolean check(int depth) {
        if(depth < 4) return true;

        for(int i=0; i < N - 1; i++) {
            for(int j=0; j < M - 1; j++) {
                if(visited[i][j] && visited[i + 1][j] && visited[i][j + 1] && visited[i + 1][j + 1]) return false;
            }
        }

        return true;
    }
}
