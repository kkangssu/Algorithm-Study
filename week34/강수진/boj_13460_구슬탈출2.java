import java.util.*;
import java.lang.*;
import java.io.*;
// The main method must be in a class named "Main".
class Main {
    static class Ball{
        int r, b, t;
        Ball(int r, int b, int t){
            this.r = r;
            this.b = b;
            this.t = t;
        }
    }
    static int n, m;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        int sr = 0;
        int sb = 0;
        int out  = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R') {
                    sr = i*m + j;
                    map[i][j] = '.'; 
                }
                else if(map[i][j] == 'B') {
                    sb = i*m + j;
                    map[i][j] = '.'; 
                }
                else if(map[i][j] == 'O') out = i*m + j;
            }
        }
        int move = bfs(sr, sb, out);
        System.out.println(move);
    }
    
    static int bfs(int sr, int sb, int out){
        int move = 0;
        boolean[][] visited = new boolean[n*m][n*m];
        Queue<Ball> que = new ArrayDeque<>();
        que.offer(new Ball(sr, sb, 0));
        visited[sr][sb] = true;
        
        while (!que.isEmpty()) {
            Ball now = que.poll();

            if(now.r == out && now.b != out) return now.t;
            if(now.t >= 10) continue;
            
            for (int i = 0; i < 4; i++) {
                Ball next = nextBall(now, i);

                if(now.r == next.r && now.b == next.b) continue;
                if(next.b == out) continue;
                if(visited[next.r][next.b]) continue;
                visited[next.r][next.b] = true;
                que.offer(next);
            }

        }
        return -1;
    }
    
    static Ball nextBall(Ball now, int d){
        int rr = now.r/m;
        int rc = now.r%m;
        int br = now.b/m;
        int bc = now.b%m;
        int fr = 0;
        int fb = 0;

        boolean redFirst = false;
        if(d == 0) redFirst = (rr < br);
        else if(d == 1) redFirst = (rc > bc);
        else if(d == 2) redFirst = (rr > br);
        else redFirst = (rc < bc);

        if(redFirst){
            fr = move(rr, rc, br, bc, d);
            fb = move(br, bc, fr/m, fr%m, d);
        }
        else{
            fb = move(br, bc, rr, rc, d);
            fr = move(rr, rc, fb/m, fb%m, d);
        }
        return new Ball(fr, fb, now.t+1);
    }

    static int move(int r, int c, int sr, int sc, int d){
        while(true){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(map[nr][nc] == '#') break;
            if(nr == sr && nc == sc && map[sr][sc] != 'O') break;
            r = nr;
            c = nc;
            if(map[r][c] == 'O') break;
        }
        return r*m+c;
    }
}
