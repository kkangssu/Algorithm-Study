import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        int N = board.length;
        int[] dr = {1,-1,0,0};
        int[] dc = {0,0,1,-1};
        int[][][] visited = new int[N][N][4];
        PriorityQueue<int[]> q =  new PriorityQueue<>(
            (o1,o2)-> {return o1[3] - o2[3];});
        
        q.add(new int[] {0,0,0,0});
        q.add(new int[] {0,0,2,0});
        
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++)
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
        }
        
        while(!q.isEmpty()){
            
            int[] idx = q.poll();
            
            int r = idx[0];
            int c = idx[1];
            int dd = idx[2];
            int cost = idx[3];
            
            if(r == N-1 && c == N-1){
                answer = Math.min(answer, cost);
                continue;
            }
            
            if(answer <= cost){
                break;
            }
            
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr >= N || nr < 0 || nc >= N || nc  < 0)
                    continue;
                
                if(board[nr][nc] == 1 || visited[nr][nc][d] <= cost){
                    continue;
                }
                
      
                if(dd == d){
                    visited[nr][nc][d]= cost + 100;
                    q.add(new int[] {nr,nc,d,cost + 100});   
                } 
                else{                 
                    visited[nr][nc][d] = cost + 600;
                     q.add(new int[] {nr,nc,d,cost+600});
                }
                
                
            }
            
            
        }
      
        return answer;
    }
}
