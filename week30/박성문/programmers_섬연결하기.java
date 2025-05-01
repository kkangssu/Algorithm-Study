import java.util.*;

class Solution {
    static int[] p;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2)->{
            return o1[2] - o2[2];
        });
        int size =costs.length;
        for(int i = 0; i < size; i++){
            q.add(costs[i]);
        }
        
        p = new int[n];
        
        for(int i = 0; i < n;i++){
            p[i] = i;
        }   
        
        while(!q.isEmpty()){
         
            int[] load = q.poll();
            int n1 = find(load[0]);
            int n2 = find(load[1]);
            int w = load[2];
            
            if(n1==n2)
                continue;
            
            answer+=w;
            p[n2] = n1;
        }
        
        return answer;
    }
    static int find(int n){
        if(p[n] == n)
            return n;
        return p[n] = find(p[n]);
    }
}
