import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] map = new int[n+1][n+1];
            
        for(int i = 0; i < results.length; i++) {
            int w = results[i][0];
            int l  =results[i][1];
            
            map[w][l] = 1;
            map[l][w] = -1;
        }
  
        for(int k =1; k <= n; k++) {
            for(int j = 1; j <=n; j++) {
                for(int i = 1; i <= n; i++) {                   
                    if(map[i][k]==1  && map[k][j] == 1){
                        map[i][j] = 1;
                        map[j][i] = -1;
                    }
                    else if(map[i][k] == -1 && map[k][j] == -1){
                        map[i][j] = -1;
                        map[j][i] = 1;
                    }
                }
            }
        }
        
        for(int i = 1; i <=n ;i++){
            int cnt = 0;
            for(int j = 1; j <=n; j++){
                if(map[i][j] != 0)
                    cnt++;
            }
            if(cnt==n-1)
                answer++;
        }
        
        return answer;
    }
}
