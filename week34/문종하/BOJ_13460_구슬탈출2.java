import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
     빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다
     빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패
    N, M (3 ≤ N, M ≤ 10)
*/
public class BOJ_13460_구슬탈출2 {
    static char [][] board;
    static boolean [][][][] v;
    static int N, M, ans = -1;
    static class Case{
        int rR, cR, rB, cB, move;
        public Case(int rR, int cR, int rB, int cB, int move) {
            this.rR = rR; this.cR = cR; this.rB = rB; this.cB = cB;
            this.move = move;
        }
    }
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N][M][N][M];
        board = new char[N][];
        for (int i = 0; i < N; i++)
            board[i] = br.readLine().toCharArray();

        solve();

        System.out.println(ans);
    }
    static void solve() {
        int rRS = 0, cRS = 0, rBS = 0, cBS = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] == 'R'){
                    rRS = i; cRS = j;
                    board[i][j] = '.';
                }else if(board[i][j] == 'B'){
                    rBS = i; cBS = j;
                    board[i][j] = '.';
                }
            }
        }

        Queue<Case> q = new ArrayDeque<>();
        q.offer(new Case(rRS, cRS, rBS, cBS, 0));
        v[rRS][cRS][rBS][cBS] = true;
        while(!q.isEmpty()){
            Case cur = q.poll();
            if(cur.move == 10)
                return;
            
            // 4방 이동
            for (int i = 0; i < 4; i++) {
                // 일직선 상 O가 존재하면 빠짐(이때 Blue도 같은 방향 같은 일직선 상에 있다면 -1) or 끝까지 감
                int rR = cur.rR;
                int cR = cur.cR;
                int rB = cur.rB;
                int cB = cur.cB;

                char ch = board[rR + dr[i]][cR + dc[i]];
                boolean flagR = false;
                boolean flagC = false;
                while(ch=='.'){
                    rR += dr[i];
                    cR += dc[i];
                    if(rR == rB && cR == cB)
                        flagC = true;
                    ch = board[rR + dr[i]][cR + dc[i]];
                }
                if(ch=='O')
                    flagR = true;

                ch = board[rB + dr[i]][cB + dc[i]];
                boolean flagB = false;
                while(ch=='.'){
                    rB += dr[i];
                    cB += dc[i];
                    ch = board[rB + dr[i]][cB + dc[i]];
                }
                if(ch=='O')
                    flagB = true;

                if(rR == rB && cR == cB){
                    if(flagC){
                        rR -= dr[i];
                        cR -= dc[i];
                    }else{
                        rB -= dr[i];
                        cB -= dc[i];
                    }
                }

                if(flagB)
                    continue;
                else if (flagR){
                    ans = cur.move + 1;
                    return;
                }

                if(v[rR][cR][rB][cB]) continue;
                v[rR][cR][rB][cB]=true;
                q.offer(new Case(rR, cR, rB, cB, cur.move + 1));
            }
        }
    }
}
