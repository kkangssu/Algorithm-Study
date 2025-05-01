import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int size = people.length;
        boolean[] visited = new boolean[size];
        int left = 0;
        for(int i = size-1; i >=0; i--){
            if(visited[i])
                break;
            
            if(people[i] + people[left] <= limit){
                visited[left] = true;
                left++;
            }     
            answer++;        
        } 
        return answer;
    }
}
