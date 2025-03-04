class Solution {
    static int max;
    static int[] arr;
    static int[] answer = {-1};
    
    public int[] solution(int n, int[] info) {
        max = -1;
        arr = new int[11];
        
        dfs(info, n, 0, 0);
        return answer;
    }
    
    static void dfs(int[] info, int n, int depth, int cnt) {
        if(depth == 11) {
            if(cnt == n) {
                int apeach = 0;
                int lion = 0;
                for(int i=0; i < 11; i++) {
                    if(info[i] == 0 && arr[i] == 0) continue;
                    if(info[i] >= arr[i]) apeach += 10 - i;
                    else lion += 10 - i;
                }
                
                if(lion > apeach) {
                    if(lion - apeach > max) {
                        max = lion - apeach;
                        answer = arr.clone();
                    } else if(lion - apeach == max) {
                        for(int i=10; i >= 0; i--) {
                            if(answer[i] < arr[i]) {
                                answer = arr.clone();
                                return;
                            } else if(answer[i] > arr[i]) return;
                        }
                    }
                }
            }
            return;
        }
        
        if(info[depth] == 0) dfs(info, n, depth + 1, cnt);
        if(cnt + 1 + info[depth] <= n) {
            arr[depth] = info[depth] + 1;
            dfs(info, n, depth + 1, cnt + 1 + info[depth]);
            arr[depth] = 0;
        }
        if(info[depth] != 0) {
            for(int i=0; i < info[depth]; i++) {
                arr[depth] = i;
                dfs(info, n, depth + 1, cnt + i);
                arr[depth] = 0;
            }
        }
    }
}
