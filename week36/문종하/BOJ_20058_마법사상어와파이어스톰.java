import java.io.*;
import java.util.*;

public class BOJ_20058_마법사상어와파이어스톰 {
    static int N, Q, size;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static boolean check(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = (int) Math.pow(2, N);
        board = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int l = Integer.parseInt(st.nextToken());
            fire(l);
        }

        int sum = 0;
        boolean[][] v = new boolean[size][size];
        int max = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += board[i][j];
                if (board[i][j] > 0 && !v[i][j])
                    max = Math.max(max, bfs(i, j, v));
            }
        }

        System.out.println(sum);
        System.out.println(max);
    }

    static void fire(int l) {
        int s = (int) Math.pow(2, l);

        for (int i = 0; i < size; i += s)
            for (int j = 0; j < size; j += s)
                rotate(i, j, s);

        melt();
    }

    static void rotate(int r, int c, int s) {
        int[][] tmp = new int[s][s];

        for (int i = 0; i < s; i++)
            for (int j = 0; j < s; j++)
                tmp[j][s - 1 - i] = board[r + i][c + j];

        for (int i = 0; i < s; i++)
            for (int j = 0; j < s; j++)
                board[r + i][c + j] = tmp[i][j];
    }

    static void melt() {
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] > 0) {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int xx = i + dx[d];
                        int yy = j + dy[d];
                        if (check(xx, yy) && board[xx][yy] > 0)
                            cnt++;
                    }
                    if (cnt < 3)
                        list.add(new int[]{i, j});
                }
            }
        }

        for (int[] p : list)
            board[p[0]][p[1]]--;
    }

    static int bfs(int r, int c, boolean[][] v) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        v[r][c] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int xx = x + dx[d];
                int yy = y + dy[d];

                if (check(xx, yy) && !v[xx][yy] && board[xx][yy] > 0) {
                    v[xx][yy] = true;
                    q.offer(new int[]{xx, yy});
                    cnt++;
                }
            }
        }

        return cnt;
    }
}