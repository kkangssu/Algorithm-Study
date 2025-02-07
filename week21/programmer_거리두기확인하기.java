import java.util.*;
class Solution {
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,-1,0,1};
    static char[][] map;
    
    public int[] solution(String[][] places) {
        int[] res = new int[5];
        for(int i =0; i < 5 ; i++){
            res[i] = 1;
        } 
        
        out : for(int t = 0; t < 5; t++){
            
            map = new char[5][5];
            
            for(int i = 0 ; i < 5; i++){
                for(int j = 0; j < 5 ; j++ ){
                    map[i][j] = places[t][i].charAt(j);
                }
            }
            
            
            for(int i = 0 ; i < 5; i++){
                for(int j = 0; j < 5 ; j++ ){
                    if(map[i][j] == 'P'){
                        if(!bfs(i,j)){
                            res[t] = 0;
                            continue out;
                        }
                    }
                }
            }
            
            
        }
        
        return res;
    }
    public boolean bfs(int r, int c){
        Queue<int[]> q = new LinkedList();
        q.offer(new int[]{r, c, 0});
        boolean[][] vis = new boolean[5][5];
        vis[r][c] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[2] > 2){
                 continue;
            }
            if(cur[2]> 0 && map[cur[0]][cur[1]]== 'P'){
                return false;
            }
            for(int d=0; d <4 ; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(!check(nr,nc) || vis[nr][nc])continue;
                if(map[nr][nc] != 'X'){
                    q.offer(new int[]{nr,nc ,cur[2]+1});
                    vis[nr][nc] = true;
                }
            }
        }
        return true;
    }
    public boolean check(int r , int c){
        return r >= 0 && c >=0 && r < 5 && c < 5;
    }
}