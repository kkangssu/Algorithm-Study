import java.util.*;
import java.io.*;

class Solution {
    
    static int[] apeach, ryan, shots;
    static int arrow, max;
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        ryan = new int[11];
        shots = new int[11];
        arrow = n;
        max = 0;

        backtrack(0, n);
        
        if (max == 0) return new int[] {-1};
        return shots;
    }
    
    static void backtrack(int idx, int left){
        if(idx == 10){
            ryan[10] = left;

            int apeachScore = 0;
            int ryanScore = 0;

            for(int i = 0; i < 10; i++){
                int score = 10-i;
                if(apeach[i] == 0 && ryan[i] == 0) continue;

                if(apeach[i] >= ryan[i]) apeachScore += score;
                else ryanScore += score;
            }

            int diff = ryanScore - apeachScore;

            if(diff > max){
                max = diff;
                shots = Arrays.copyOf(ryan, 11);
            }
            else if(diff == max){
                if(check()){
                    shots = Arrays.copyOf(ryan, 11);
                }
            }

            return;
        }

        int a = apeach[idx] + 1;
        if(left >= a){
            ryan[idx] = a;
            backtrack(idx+1, left - a);
        }

        ryan[idx] = 0;
        backtrack(idx+1, left);
    }

    static boolean check(){
        for(int i = 10; i >= 0; i--){
            if(ryan[i] == shots[i]) continue;
            
            if(ryan[i] > shots[i]) return true;
            else if(ryan[i] < shots[i]) return false;
        }
        return false;
    }
}
