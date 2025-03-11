class Solution {
    static int[] map;
    static int[] tmp;
    static int max;
    static int N;
    static int[] score;
    public int[] solution(int n, int[] info) {
        int[] answer={};
        map = info;
        N = n;
        tmp = new int[11];
       
        max = -1;
        score = new int[1];
        
        perm(0,0);
        
        if(score.length == 1) {
            answer = new int[] {-1};
        } else{
            answer = new int[11];
            answer = score;
        }
        
        return answer;
    }
    
    static void perm(int cnt, int start){
        
        if(cnt == N){
            
            int lion = 0;
            int ap = 0;
            
            for(int i = 0; i < 11; i++){
                if(tmp[i] > map[i]){
                    lion += (10-i);
                } else{
                    if(map[i]>=1)
                        ap += (10-i);
                }
            }
            
            if(lion > ap){
                
                if(lion - ap> max) {
                    
                    max = lion - ap;
                    score = new int[11];
                    
                    for(int i = 0; i < 11; i++){
                        score[i] = tmp[i];
                    }

                } else if(lion-ap == max){
                                        
                    for(int j = 10; j >= 0; j--){
          
                        if(tmp[j] == score[j])
                            continue;
                        if(tmp[j] > score[j]){
                            score = new int[11];
                    
                            for(int i = 0; i < 11; i++){
                                score[i] = tmp[i];
                            }
                             break;
                        }  else{
                            break;
                        }
                        
                  
                        
                    }
                }
                
            }
            return;
        }
        
        
        for(int i = start; i < 11; i++){
            tmp[i]++;
            perm(cnt+1, i);
            tmp[i]--;
        }
        
    }
    
}
