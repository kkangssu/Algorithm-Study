package 강수진;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class progrmmers_경주로건설 {
    static class Move{
        int r, c, d, money;
        Move(int r, int c, int d, int money){
            this.r = r;
            this.c = c;
            this.d = d;
            this.money = money;

        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int R, C;

    public static int solution(int[][] board) {
        R = board.length;
        C = board[0].length;

        int answer = bfs(board);
        System.out.println(answer);
        return answer;
    }

    static int bfs(int[][] map) {
        Queue<Move> que = new ArrayDeque<>();
        que.offer(new Move(0, 0, 1, 0));
        que.offer(new Move(0, 0, 2, 0));

        int[][][] visited = new int[R][C][4];
        for(int i = 1; i < R; i++) {
            for(int j = 0; j < C; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        int min = Integer.MAX_VALUE;

        while(!que.isEmpty()) {
            Move m = que.poll();
            int r = m.r;
            int c = m.c;
            int d = m.d;
            int money = m.money;
            if(money > min) continue;
            if(m.r == R-1 && m.c == C-1) {
                min = Math.min(min, m.money);
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(!check(nr, nc)) continue;
                if(map[nr][nc] == 1) continue;

                int nmoney = money + 100;
                if(m.d != i) nmoney += 500;

                if(visited[nr][nc][i] <= nmoney) continue;

                visited[nr][nc][i] = nmoney;
                que.offer(new Move(nr, nc, i, nmoney));
            }
        }

        return min;
    }

    static boolean check(int r, int c){
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
