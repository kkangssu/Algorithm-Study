package week21.강수진;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
public class boj_14442_벽부수고이동하기2 {
    static class RC{
        int r, c, d, k;
        RC(int r, int c, int d, int k){
            this.r = r;
            this.c = c;
            this.d = d;
            this.k = k;
        }
    }
    static int N, M, K;
    static int[][] map;
    static int[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }
        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], K + 1);  // K보다 큰 값으로 초기화
        }
        System.out.println(bfs());
    }
    static int bfs() {
        Queue<RC> que = new ArrayDeque<>();
        que.add(new RC(0, 0, 1, 0));
        visited[0][0] = 0;
        while (!que.isEmpty()) {
            RC now = que.poll();
            int r = now.r;
            int c = now.c;
            int d = now.d;
            int k = now.k;
            //System.out.println("r: " + r + ", c: " + c + ", d: " + d + ", k: " + k);
            if(r == N-1 && c == M-1) return d;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nd = d + 1;
                int nk = k;
                if(!check(nr, nc)) continue;
                if(map[nr][nc] == 1) nk++;
                if(nk > K) continue;

                if(visited[nr][nc] > nk) {
                    visited[nr][nc] = nk;
                    que.add(new RC(nr, nc, nd, nk));
                }
            }
        }
        return -1;
    }
    static boolean check(int r, int c) {
        return r < N && r >= 0 && c < M && c >= 0;
    }
}