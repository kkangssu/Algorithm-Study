import java.util.*;
import java.io.*;

class Main {

    static class RC implements Comparable<RC>{
        int r, c, fuel;
        RC(int r, int c, int fuel){
            this.r = r;
            this.c = c;
            this.fuel = fuel;
        }
        RC(int r, int c){
            this.r = r;
            this.c = c;
            this.fuel = 0;
        }
        @Override
        public int compareTo(RC o){
            if(this.fuel != o.fuel) return o.fuel - this.fuel;
            else if(this.r != o.r) return this.r - o.r;
			else return this.c - o.c;
        }
    }
    static int M, N, F;
    static int[][] map;
    static RC[] dest;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static RC start;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dest = new RC[M+2];
        st = new StringTokenizer(br.readLine());
        start = new RC(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, F);
        for(int i = 2; i < M+2; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = i;
            dest[i] = new RC(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        }

        int result = F;
        for(int i = 0; i < M; i++){
            if(findNext()) result = start.fuel;
            else{
                result = -1;
                break;
            }
        }

        System.out.println(result);
    }

    static boolean findNext(){
        Queue<RC> pq = new PriorityQueue<>();
        pq.offer(start);
        boolean[][] visited = new boolean[N][N];
        visited[start.r][start.c] = true;
        while(!pq.isEmpty()){
            RC now = pq.poll();
            int r = now.r;
            int c = now.c;
            int f = now.fuel;
            if(map[r][c] > 1){
                if(drive(now, map[r][c])) {
                    map[r][c] = 0;
                    return true;
                }
                return false;
            }
            if(f == 0) continue;
            
            for(int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(!check(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc] == 1) continue;

                visited[nr][nc] = true;
                pq.offer(new RC(nr, nc, f-1));
            }
        }
        return false;
    }

    static boolean drive(RC passenger, int idx){
        boolean[][] visited = new boolean[N][N];
        Queue<RC> que = new ArrayDeque<>();
        que.offer(passenger);
        visited[passenger.r][passenger.c] = true;        
        while(!que.isEmpty()){
            RC now = que.poll();
            int r = now.r;
            int c = now.c;
            int f = now.fuel;
            if(r == dest[idx].r && c == dest[idx].c) {
            int charge = passenger.fuel*2 - f;
                start = new RC(r, c, charge);
                return true;
            }
            if(f == 0) continue;

            for(int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(!check(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc] == 1) continue;

                visited[nr][nc] = true;
                que.offer(new RC(nr, nc, f-1));
            }
        }
        return false;
    }

    static boolean check(int r, int c){
        return r < N && r >= 0 && c < N && c >= 0;   
    }
}
