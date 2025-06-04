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
    static char[][] map;
    static final int rr = 12;
    static final int cc = 6;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[rr][cc];
        for(int i = rr-1; i >= 0; i--){
            String str = br.readLine();
            for(int j = 0; j < cc; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int cnt = 0;
        while(true){
            if(!puyo()) break;
            gravity();
            cnt++;
        }

        System.out.println(cnt);
    }

    static boolean puyo(){
        boolean[][] visited = new boolean[rr][cc];
        boolean flag = false;
        
        for(int i = 0; i < rr; i++){
            for(int j = 0; j < cc; j++){
                if(visited[i][j] || map[i][j] == '.') continue;
                Queue<RC> que = new ArrayDeque<>();
                Queue<RC> bfs = new ArrayDeque<>();

                que.offer(new RC(i, j));
                bfs.offer(new RC(i, j));
                visited[i][j] = true;

                while(!bfs.isEmpty()){
                    RC now = bfs.poll();
                    for(int idx = 0; idx < 4; idx++){
                        int nr = now.r + dr[idx];
                        int nc = now.c + dc[idx];

                        if(!check(nr, nc)) continue;
                        if(visited[nr][nc]) continue;
                        if(map[nr][nc] != map[i][j]) continue;

                        visited[nr][nc] = true;
                        que.offer(new RC(nr, nc));
                        bfs.offer(new RC(nr, nc));
                    }
                }

                if(que.size() >= 4){
                    flag = true;
                    while(!que.isEmpty()){
                        RC now = que.poll();
                        map[now.r][now.c] = '.';
                    }
                }
            }
        }
        return flag;
    }

    static void gravity(){
        for(int i = 0; i < cc; i++){
            Queue<Character> que = new ArrayDeque<>();
            for(int j = 0; j < rr; j++){
                if(map[j][i] != '.') que.offer(map[j][i]);
            }
            
            for(int j = 0; j < rr; j++){
                if(!que.isEmpty()) map[j][i] = que.poll();
                else map[j][i] = '.';
            }
        }
    }

    static boolean check(int r, int c){
        return r >= 0 && r < rr && c >= 0 && c < cc;
    }
}
