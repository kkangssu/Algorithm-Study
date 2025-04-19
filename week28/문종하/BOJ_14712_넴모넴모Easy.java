import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14712_넴모넴모Easy {
    static int N,M;
    static long ans;
    static boolean[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new boolean[N+1][M+1];
        solve(0);

        System.out.println(ans);
    }
    static void solve(int depth) {
        if (depth == N * M) {
            ans++;
            return;
        }

        int row = depth / M + 1;
        int col = depth % M + 1;

        solve(depth + 1);

        if (!check(row, col)) {
            board[row][col] = true;
            solve(depth + 1);
            board[row][col] = false;
        }
    }
    static boolean check(int r, int c) {
        if (r > 1 && c > 1 && board[r-1][c] && board[r][c-1] && board[r-1][c-1]) {
            return true;
        }
        return false;
    }
}