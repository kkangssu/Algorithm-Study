import java.util.*;
class Solution {

    static int[][] map;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        map = new int[n+1][n+1];
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i==j)
                    map[i][j] = 0;
                else
                    map[i][j] = 100000 * 200 + 1;
            }
        }
        
        for(int i = 0; i < fares.length; i++){
            map[fares[i][0]][fares[i][1]] = fares[i][2];
            map[fares[i][1]][fares[i][0]] = fares[i][2];
        }
       
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
       
        
        //System.out.println(Arrays.deepToString(map));
        
        for(int i = 1; i <= n; i++){
            answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);
        }
        
        return answer;
    }
}
