import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int cnt = 0;
        int idx = 0;
        for(int i = people.length-1; i >= idx; i--){
            //System.out.println(i + " " +idx+" "+ cnt);
            if(i == idx) cnt++;
            else if(people[i] + people[idx] <= limit){
                cnt++;
                idx++;
            }
            else cnt++;
        }
        return cnt;
    }
}
