import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16988_Baaaaaaaaaduk2 {

    static class RC{
        int r, c;
        RC(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for(int i = 0; i < N*M; i++){
            for(int j = i+1; j < N*M; j++){
                int r1 = i / M;
                int r2 = j / M;
                int c1 = i % M;
                int c2 = j % M;
                if(map[r1][c1] != 0) continue;
                if(map[r2][c2] != 0) continue;
                map[r1][c1] = 1;
                map[r2][c2] = 1;
                max = Math.max(max, baduk2());
                map[r1][c1] = 0;
                map[r2][c2] = 0;
            }
        }

        System.out.println(max);
    }

    static int baduk2(){
        int result = 0;
        Queue<RC> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 2) continue;
                if(visited[i][j]) continue;
                que.offer(new RC(i, j));
                visited[i][j] = true;
                int cnt = 1;
                boolean kill = true;
                while(!que.isEmpty()){
                    RC now = que.poll();
                    for(int k = 0; k < 4; k++){
                        int r = now.r + dr[k];
                        int c = now.c + dc[k];
                        if(!check(r, c)) continue;
                        if(visited[r][c]) continue;
                        if(map[r][c] == 0) kill = false;
                        if(map[r][c] == 1) continue;

                        visited[r][c] = true;
                        que.offer(new RC(r, c));
                        cnt++;
                    }
                }
                if(!kill) continue;
                result += cnt;
            }
        }
        return result;
    }


    static boolean check(int r, int c){
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
