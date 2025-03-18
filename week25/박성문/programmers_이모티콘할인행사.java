import java.util.*;
class Solution {
    static int a1;
    static int a2;
    static int[] answer;
    static int[] sale = new int[] {10,20,30,40};
    static int[] tmp;
    static int N;
    static int[][] u;
    static int[] e;
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        u = users;
        e = emoticons;
        
        N = emoticons.length;
        tmp = new int[N];
        
        choice(0);
            
        return answer;
    }
    
    static void choice(int cnt) {
        
        if(cnt == N) {
            int count = 0;
            int sum = 0;
            for(int i = 0; i < u.length; i++) {
                int tmpSum = 0;
                for(int j = 0; j < N; j++) {
                    if(u[i][0] <= tmp[j]) {
                        tmpSum += e[j]- (((double)tmp[j]/100)) * e[j];
                    }
                    if(tmpSum >= u[i][1])
                        break;
                }
                
                if(tmpSum >= u[i][1]){
                    count++;
                } else{
                    sum += tmpSum;
                }
                

   
            }
            
            if(answer[0] < count){
                answer[0] = count;
                answer[1] = sum;
            }
            if(answer[0] == count){
                if(sum > answer[1]){
                    answer[0] = count;
                    answer[1] = sum;
                }
            }
            return;
            
        }
        
        
        for(int i = 0; i < 4; i++){
            
            tmp[cnt] = sale[i];
            choice(cnt+1);
        }
        
    }
    
}
