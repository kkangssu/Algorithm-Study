import java.util.*;
class Solution {
    static int N, answer;
    static int[] dr = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dc = {1, 0, -1, 0}; // 우 하 좌 상
    
    static int[][] map;
    
    static class Car{
        int r,c,cnt;
        int dir = 0;

        public Car(int r, int c, int cnt, int dir){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
        @Override
        public String toString(){
            return "[r : "+ r +"] "+  "[c : "+ c +"] " + "[cnt : " + cnt + "] " + "[dir : " + dir+"]";
        }
    }
    public int solution(int[][] board) {
        map = board;
        N = board.length;
        answer = Integer.MAX_VALUE;
        bfs();
        return answer;
    }
    static void bfs(){
        Queue<Car> q = new LinkedList<>();
        int[][] vis = new int[N][N];
        if(map[0][1] != 1){
            q.offer(new Car(0,1,100,0));
            vis[0][1] = 100;    
        }
        
        if(map[1][0] != 1){
            q.offer(new Car(1,0,100,1));
            vis[1][0] = 100;
        }
        
        while(!q.isEmpty()){
            Car cur = q.poll();
            
            if(cur.r == N-1 && cur.c == N-1 ){
                answer = Math.min(answer, cur.cnt);
                continue;
            }            
            for(int d = 0 ; d < 4 ; d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if(!check(nr,nc) || map[nr][nc] == 1)continue;
            
                int cost = cur.dir == d ? 100 : 600;
                if(vis[nr][nc] == 0){
                    q.offer(new Car(nr, nc, cur.cnt+cost , d));
                    vis[nr][nc] = cur.cnt + cost;
                }else{
                    if(vis[nr][nc] + 500 > cur.cnt + cost){
                        q.offer(new Car(nr, nc, cur.cnt+cost , d));
                        vis[nr][nc] = cur.cnt + cost;
                    }
                }
                
            }
        }
    }
    
    static boolean check(int r, int c){
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}

/*
테스트 1 〉	통과 (0.47ms, 89.7MB)
테스트 2 〉	통과 (0.35ms, 73.9MB)
테스트 3 〉	통과 (0.37ms, 70.8MB)
테스트 4 〉	통과 (0.34ms, 76.7MB)
테스트 5 〉	통과 (0.36ms, 72MB)
테스트 6 〉	통과 (1.23ms, 73.7MB)
테스트 7 〉	통과 (1.15ms, 69.5MB)
테스트 8 〉	통과 (1.16ms, 81.1MB)
테스트 9 〉	통과 (0.52ms, 75.4MB)
테스트 10 〉	통과 (1.01ms, 74.9MB)
테스트 11 〉	통과 (15.63ms, 93.3MB)
테스트 12 〉	통과 (1.70ms, 97.5MB)
테스트 13 〉	통과 (0.76ms, 94.5MB)
테스트 14 〉	통과 (0.88ms, 83MB)
테스트 15 〉	통과 (1.31ms, 90.3MB)
테스트 16 〉	통과 (1.72ms, 96.4MB)
테스트 17 〉	통과 (4.36ms, 93.6MB)
테스트 18 〉	통과 (5.82ms, 89.8MB)
테스트 19 〉	통과 (12.89ms, 88.8MB)
테스트 20 〉	통과 (1.31ms, 82.9MB)
테스트 21 〉	통과 (1.21ms, 96.5MB)
테스트 22 〉	통과 (0.42ms, 89.4MB)
테스트 23 〉	통과 (0.50ms, 68.2MB)
테스트 24 〉	통과 (0.41ms, 86.8MB)
테스트 25 〉	통과 (0.35ms, 78.2MB)
*/
 