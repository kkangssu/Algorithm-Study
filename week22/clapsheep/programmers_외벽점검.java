import java.util.*;
class Solution {
    static int[] WEAK, DIST, perm; 
    static int res, weakCnt, distCnt;
    static boolean[] vis;
    
    public int solution(int n, int[] weak, int[] dist) {
        res = Integer.MAX_VALUE;
        weakCnt = weak.length;
        distCnt = dist.length;
        WEAK = new int[weakCnt * 2]; // 취약점 배열(원형)
        DIST = dist;
        vis = new boolean[distCnt]; // 방문 배열
        perm = new int[distCnt]; // 친구 순열
        
        
        int temp = 0;
        while(temp < weakCnt){
            WEAK[temp] = weak[temp];
            WEAK[temp + weakCnt] = weak[temp]+n;
            temp++;
        }
        
        for(int i = 0 ; i < weakCnt; i++){  // 취약점 마다 시작해보기
            dfs(i, 0);
        }
        
        if(res == Integer.MAX_VALUE) return -1;
        return res;
        
    }
    static void dfs(int start, int cnt){  // 친구들 배치하는 모든 순서
        if(cnt == distCnt){
            res = Math.min(res, check(start,start+WEAK.length/2));
            return;
        }
        for(int i = 0 ; i < distCnt ; i++){
            if(vis[i]) continue;
            
            vis[i] = true;
            perm[cnt] = DIST[i];
            dfs(start, cnt+1);
            vis[i] = false;
        }
    }
    static int check(int start, int end){
        int f= 1;
        int pos = WEAK[start] + perm[f-1];
        for(int i = start ; i < end; i++){
            if(pos < WEAK[i]){
                f++;
                if(f > perm.length){
                    return Integer.MAX_VALUE;
                }
                pos =WEAK[i] + perm[f-1];
            }
        }
        return f;
    }
}