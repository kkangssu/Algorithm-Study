import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static char[][] students;
    static int res;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    static char[] list;
    static int[] team;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        students = new char[5][5];
        list = new char[25];
        for(int i = 0 ;i < 5 ; i++){
            students[i] = br.readLine().toCharArray();
            for(int j = 0 ; j < 5 ; j++){
                list[i*5+j] = students[i][j];
            }
        }
        team = new int[7];
        dfs(0,0,0);
        System.out.println(res);
        
    }
    static void dfs(int cnt, int start, int y){
        if(y > 3)return;
        if(cnt == 7){
            if(isAvailable()) res++;
            return;
        }
        for(int i = start; i < 25 ; i++){
            team[cnt] = i;
            dfs(cnt+1, i+1, list[i]=='Y'?y+1:y);          
            team[cnt] = 0;
        }
        
        
    }
    static boolean isAvailable(){
        boolean[][] map = new boolean[5][5];
        for(int i =0; i < 7; i++){
            int r = team[i]/5;
            int c = team[i]%5;
            map[r][c] = true;
        }        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[5][5];
        int nowR = team[0]/5;
        int nowC = team[0]%5;
        q.offer(new int[]{nowR,nowC});
        vis[nowR][nowC] = true;
        int cnt =0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(map[cur[0]][cur[1]])cnt++;
            for(int d= 0; d < 4 ; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(!check(nr,nc) || vis[nr][nc] || !map[nr][nc])continue;
                q.offer(new int[]{nr,nc});
                vis[nr][nc] = true;
            }
        }
        return cnt == 7;
    }
    static boolean check(int r, int c){
        return r >=0 && c >= 0 && r < 5 && c < 5;
    }
}


