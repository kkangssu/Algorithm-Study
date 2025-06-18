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
    static int n, m, t, result;
    static int[][] video;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        video = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                video[i][j] = cal(Integer.parseInt(st.nextToken()), 
                                 Integer.parseInt(st.nextToken()),
                                 Integer.parseInt(st.nextToken()));
            }
        }

        t = Integer.parseInt(br.readLine());
        bfs();

        System.out.println(result);
    }

    static int cal(int a, int b, int c){
        return (a + b + c)/3;
    }

    static void bfs(){
        boolean[][] visited = new boolean[n][m];
        Queue<RC> que = new ArrayDeque<>();
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j]) continue;
                if(video[i][j] < t) {
                    visited[i][j] = true;
                    continue;
                }

                que.offer(new RC(i, j));
                while(!que.isEmpty()){
                    RC now = que.poll();
                    for(int d = 0; d < 4; d++){
                        int nr = now.r + dr[d];
                        int nc = now.c + dc[d];

                        if(!check(nr, nc)) continue;
                        if(visited[nr][nc]) continue;
                        if(video[nr][nc] < t){
                            visited[nr][nc] = true;
                            continue;
                        }

                        visited[nr][nc] = true;
                        que.offer(new RC(nr, nc));
                    }
                }

                result++;
            }
        }
    }

    static boolean check(int r, int c){
        return r < n && r >= 0 && c < m && c >= 0;
    }
}
