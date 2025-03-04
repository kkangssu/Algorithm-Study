import java.util.*;
import java.io.*;

class Solution {
    
    static Map<String, Integer> map;
    static boolean[] visited, visitedBit;
    static int result;
    
    public int solution(String[] user_id, String[] banned_id) {
         map = new HashMap<>();
        int idx = 1;
        int n = 0;
        for(int i = 0; i < user_id.length; i++){
            map.put(user_id[i], idx);
            n += idx;
            idx <<= 1;
        }
        visitedBit = new boolean[n+1];
        visited = new boolean[user_id.length];

        perm(0, 0, user_id, banned_id);

        return result;
    }
    
    static void perm(int cnt, int sum, String[] user_id, String[] banned_id){
        if(cnt == banned_id.length){
            if(!visitedBit[sum]) {
                result++;
                visitedBit[sum] = true;
            }
            return;
        }
        String str1 = banned_id[cnt];
        for(int i = 0; i < user_id.length; i++){
            String str2 = user_id[i];
            if(visited[i]) continue;
            if(!check(str1, str2)) continue;
            visited[i] = true;
            perm(cnt+1, sum + map.get(str2), user_id, banned_id);
            visited[i] =  false;
        }
        return;
    }

    static boolean check(String s1, String s2){
        if(s1.length() != s2.length()) return false;
        for(int i = 0; i < s1.length(); i++){
            char ch = s1.charAt(i);
            if(ch == '*') continue;
            if(ch != s2.charAt(i)) return false;
        }
        return true;
    }
}
