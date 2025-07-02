import java.io.*;
import java.util.*;

public class BOJ_16988_Baaaaaaaaaduk2 {
    static int N, M, max;
    static int[][] board;
    static boolean[][] v;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<int[]> blank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        blank = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0)
                    blank.add(new int[]{i, j});
            }
        }

        // 빈 칸 2곳 선택 조합
        for (int i = 0; i < blank.size(); i++)
            for (int j = i + 1; j < blank.size(); j++) {
                int[] pos1 = blank.get(i);
                int[] pos2 = blank.get(j);

                board[pos1[0]][pos1[1]] = 1;
                board[pos2[0]][pos2[1]] = 1;

                // kill count
                int cnt = calc();
                max = Math.max(max, cnt);

                board[pos1[0]][pos1[1]] = 0;
                board[pos2[0]][pos2[1]] = 0;
            }

        System.out.println(max);
    }

    static int calc() {
        v = new boolean[N][M];
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 2 && !v[i][j]) {
                    List<int[]> group = new ArrayList<>();

                    // 자유도x => 죽음
                    if (!dfs(i, j, group))
                        sum += group.size();
                }
            }
        }

        return sum;
    }

    static boolean dfs(int x, int y, List<int[]> group) {
        v[x][y] = true;
        group.add(new int[]{x, y});
        boolean flag = false;

        for (int d = 0; d < 4; d++) {
            int xx = x + dx[d];
            int yy = y + dy[d];

            if (check(xx,yy)) continue;

            if (board[xx][yy] == 0)
                // 인접 => 자유도가 있음
                flag = true;
            else if (board[xx][yy] == 2 && !v[xx][yy])
                // 같은 색 돌
                if (dfs(xx, yy, group)) flag = true;
        }

        return flag;
    }

    static boolean check(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}