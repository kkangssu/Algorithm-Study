import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N,M;
    static int[][] map;
    static long answer;
    static int[] dr = {-1,0,-1};
    static int[] dc = {0,-1,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        answer = 0;

        dfs(0,0);
        System.out.println(answer);
        
    }

    static void dfs(int r, int c) {
        if(r >= N) {
            answer++;
            return;
        }
        int cnt = 0;
        
        for(int d = 0; d < 3; d++){
            int nr = r +dr[d];
            int nc = c + dc[d];

            if(check(nr,nc))
                break;

            if(map[nr][nc] == 1){
                cnt++;
            }
            
        }

        if(cnt != 3) {
          map[r][c] = 1;

         if(c < M-1){
            dfs(r,c+1);
            map[r][c] = 0;
            dfs(r,c+1);
            return;
        } else{
            dfs(r+1,0);
            map[r][c] = 0;
            dfs(r+1,0);
            return;
        }
        }
  
        if(c < M-1){
            dfs(r,c+1);
            return;
        } else{
            dfs(r+1,0);
            return;
        }
        
    }

    
    static boolean check(int r, int c) {
        return r>=N || r < 0 || c >= M || c < 0;
    }
}
