import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685_드래곤커브 {
    // RULD
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new boolean[101][101];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            solve(y, x, d, g);
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                if (board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1])
                    cnt++;

        System.out.println(cnt);
    }

    private static void solve(int y, int x, int d, int g) {
        List<Integer> dirs = new ArrayList<>();
        dirs.add(d);

        for (int i = 0; i < g; i++)
            for (int j = dirs.size() - 1; j >= 0; j--)
                dirs.add((dirs.get(j) + 1) % 4);

        board[y][x] = true;

        for (int dir : dirs) {
            y += dy[dir];
            x += dx[dir];
            board[y][x] = true;
        }
    }
}