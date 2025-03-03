import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, res;
    static int[][] adj;
    static boolean[] team;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new int[N+1][N+1];
        for(int i = 1; i < N+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++){
                adj[i][j] =Integer.parseInt(st.nextToken());
            }
        }
        team = new boolean[N];
        res = Integer.MAX_VALUE;
        dfs(0,0);
        System.out.println(res);
        
            
    }
    static void dfs(int cnt, int size){
        if(res == 0)return;
        if(cnt == N){

            if(size > N/2)return;
            //System.out.println(size);
            //System.out.println(Arrays.toString(team));
            int a = 0;
            int b = 0;
            int cntA = 0;
            int cntB = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N ; j++){
                    if(team[i] && team[j]){
                        a+= adj[i+1][j+1];
                        cntA++;
                    }else if(!team[i] && !team[j]){
                        b+= adj[i+1][j+1];
                        cntB++;
                    }
                }
            }
            if(cntA==0 || cntB==0)return;
            res = Math.min(Math.abs(a-b), res);
            return;
                
        }
        
        team[cnt] = true;
        dfs(cnt+1 , size+1);
        team[cnt] = false;
        dfs(cnt+1, size);
        
    }
}