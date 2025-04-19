package 강수진;

/*
테스트 1 〉	통과 (0.56ms, 83.8MB)
테스트 2 〉	통과 (0.97ms, 76.9MB)
테스트 3 〉	통과 (14.47ms, 77.2MB)
테스트 4 〉	통과 (15.46ms, 74.1MB)
테스트 5 〉	통과 (0.85ms, 80.9MB)
테스트 6 〉	통과 (8.77ms, 88.8MB)
테스트 7 〉	통과 (1.16ms, 72.2MB)
테스트 8 〉	통과 (0.45ms, 90.3MB)
테스트 9 〉	통과 (0.47ms, 73.1MB)
테스트 10 〉	통과 (32.43ms, 75.8MB)
테스트 11 〉	통과 (23.09ms, 91.3MB)
테스트 12 〉	통과 (28.16ms, 89.7MB)
테스트 13 〉	통과 (33.26ms, 85.8MB)
테스트 14 〉	통과 (24.43ms, 75.7MB)
테스트 15 〉	통과 (29.15ms, 72.6MB)
테스트 16 〉	통과 (18.71ms, 75.8MB)
테스트 17 〉	통과 (20.57ms, 89.6MB)
테스트 18 〉	통과 (25.08ms, 73.2MB)
테스트 19 〉	통과 (19.25ms, 88.7MB)
테스트 20 〉	통과 (17.25ms, 81.6MB)
테스트 21 〉	통과 (16.98ms, 94.8MB)
테스트 22 〉	통과 (0.61ms, 93.2MB)
테스트 23 〉	통과 (0.38ms, 86MB)
테스트 24 〉	통과 (0.41ms, 89.4MB)
테스트 25 〉	통과 (8.67ms, 89.9MB)
 */

import java.util.*;
import java.io.*;

public class programmers_외벽점검 {

    static int[] arr;
    static int[] dist2, friends;
    static int min, f, w;
    static boolean[] visited;

    public int solution(int n, int[] weak, int[] dist) {
        Arrays.sort(weak);
        arr = new int[weak.length*2];
        dist2 = dist;
        f = dist.length;
        w = weak.length;
        min = Integer.MAX_VALUE;
        friends = new int[f];
        visited = new boolean[f];
        for(int i = 0; i < weak.length; i++){
            arr[i] = weak[i];
            arr[weak.length+i] = weak[i] + n;
        }

        perm(0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    static void min(){
        for(int i = 0; i < w; i++){
            int num = 1;
            int d = arr[i] + friends[0];
            int l = 0;
            for(l = i; l < i+w; l++){
                if(arr[l] <= d) continue;
                if(num >= f) break;
                d = arr[l] + friends[num];
                num++;
            }

            if(l >= i + w){
                min = Math.min(min, num);
            }
        }
    }
    static void perm(int idx){
        if(idx == f){
            min();
            return;
        }
        for(int i = 0; i < f; i++){
            if(visited[i]) continue;
            visited[i] = true;
            friends[idx] = dist2[i];
            perm(idx+1);
            visited[i] = false;
        }
    }
}
