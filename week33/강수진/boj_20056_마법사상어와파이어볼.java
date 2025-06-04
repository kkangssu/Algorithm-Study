import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class fire{
        int r, c, m, s, d;
        fire(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int N, M, K;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static List<fire> balls = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            balls.add(new fire(r, c, m, s, d));
        }

        for(int i = 0; i < K; i++){
            move();
            merge();
        }

        int result = 0;
        for(fire f: balls){
            result += f.m;
        }

        System.out.println(result);
    }

    static void move(){
        for(fire f: balls){
            f.r = (f.r + dr[f.d]*f.s) % N;
            if(f.r < 0) f.r += N;
            f.c = (f.c + dc[f.d]*f.s) % N;
            if(f.c < 0) f.c += N;
        }
    }

    static void merge(){
        List<Integer>[] map = new ArrayList[N*N];
        List<fire> newBalls = new ArrayList<>();
        for(int i = 0; i < N*N; i++){
            map[i] = new ArrayList<>();
        }
        
        int idx = 0;
        for(fire f: balls){
            map[f.r*N + f.c].add(idx++);
        }

        for(int i = 0; i < N*N; i++){
            if(map[i].size() == 0) continue;
            if(map[i].size() == 1) {
                newBalls.add(balls.get(map[i].get(0)));
                continue;
            }
            int sumM = 0;
            int sumS = 0;
            int di = balls.get(map[i].get(0)).d % 2;
            boolean flag = true;
            for(int iFire: map[i]){
                sumM += balls.get(iFire).m;
                sumS += balls.get(iFire).s;
                if(flag && di != balls.get(iFire).d % 2){
                    flag = false;
                }
            }

            if(sumM/5 == 0) continue;
            
            for(int j = 0; j < 8; j+=2){
                newBalls.add(new fire(i/N, i%N, sumM/5, sumS/map[i].size(), flag?j:j+1));
            }
        }

        balls = newBalls;
    }
}
