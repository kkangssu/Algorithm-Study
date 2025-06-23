import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class RC{
        int r, c;
        RC(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int[][] map;
    static int n, ice;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        n = 1 << N;
        map = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                ice += map[i][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Q; i++){
            int L = Integer.parseInt(st.nextToken());
            turn(1 << L);
            dec();
        }

        int max = bfs();
        
        System.out.println(ice);
        System.out.println(max);
    }

    static void turn(int l){
        for(int i = 0; i < n/l; i++){
            for(int j = 0; j < n/l; j++){
                for(int r = 0; r < l; r++){
                    for(int c = r+1; c < l; c++){
                        int r1 = l*i + r;
                        int c1 = l*j + c;
                        int r2 = l*i + c;
                        int c2 = l*j + r;

                        int tmp = map[r1][c1];
                        map[r1][c1] = map[r2][c2];
                        map[r2][c2] = tmp;
                    }
                }

                for(int r = 0; r < l; r++){
                    for(int c = 0; c < l/2; c++){
                        int r1 = l*i + r;
                        int c1 = l*j + c;
                        int c2 = l*j + (l-1-c);

                        int tmp = map[r1][c1];
                        map[r1][c1] = map[r1][c2];
                        map[r1][c2] = tmp;
                    }
                }
            }
        }
    }

    static void dec(){
        Queue<RC> que = new ArrayDeque<>();
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                if(map[r][c] == 0) continue;
                int t = 0;
                for(int i = 0; i < 4; i++){
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(!check(nr, nc)) continue;
                    if(map[nr][nc] == 0) continue;
                    t++;
                }
                if(t < 3) que.offer(new RC(r, c));
            }
        }

        while(!que.isEmpty()){
            RC now = que.poll();
            map[now.r][now.c]--;
            ice--;
        }
    }

    static boolean check(int r, int c){
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    static int bfs(){
        boolean[][] visited = new boolean[n][n];
        int max = 0;
        Queue<RC> que = new ArrayDeque<>();
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                if(map[r][c] == 0) continue;
                if(visited[r][c]) continue;
                int num = 1;
                que.offer(new RC(r, c));
                visited[r][c] = true;

                while(!que.isEmpty()){
                    RC now = que.poll();
                    for(int i = 0;  i< 4; i++){
                        int nr = now.r + dr[i];
                        int nc = now.c + dc[i];
                        if(!check(nr, nc)) continue;
                        if(map[nr][nc] == 0) continue;
                        if(visited[nr][nc]) continue;
                        num++;
                        que.offer(new RC(nr, nc));
                        visited[nr][nc] = true;
                    }
                }

                max = Math.max(max, num);
            }
        }
        return max;
    }
}
