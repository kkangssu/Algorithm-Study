import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int[][] map = routes;
        
        Arrays.sort(map, (o1,o2)-> {
            return o1[1] - o2[1];
        });
            
        int tmp = map[0][1];
        answer++;
        
        for(int i = 1; i < routes.length; i++){
            if(tmp >= map[i][0] && tmp <= map[i][1]){
                continue;
            } else{
                answer++;
                tmp = map[i][1];
            }
        }

        return answer;
    }
}
