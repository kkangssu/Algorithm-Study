import java.util.*;
class Solution {
    static List<Integer>[] banned;
    static int uCnt, bCnt, answer;
    static int[] list;
    public int solution(String[] user_id, String[] banned_id) {
        
        uCnt = user_id.length; // 유저 아이디 갯수
        bCnt = banned_id.length; // 밴 아이디 갯수
        answer = 0;
        
        banned = new ArrayList[bCnt]; // 각 이름이 몇번째 밴에 들어갈 수 있는지
        for(int i = 0; i < bCnt; i++){
            banned[i] = new ArrayList<>();
        }
        list = new int[bCnt];
        
        for(int b = 0; b < bCnt; b++ ){ 
            char[] curBan = banned_id[b].toCharArray();
            int N = curBan.length;
            for(int u = 0; u < uCnt; u++ ){
                char[] curUser = user_id[u].toCharArray();
                boolean temp = true;
                if(curUser.length == N){
                    for(int n = 0; n < N; n++){
                        if(curBan[n] != '*'){
                            if(curBan[n]!= curUser[n]){
                                temp = false;
                                break;
                            }
                        }
                    }
                }else{
                    temp = false;
                }
                if(temp){
                    banned[b].add(u);
                }
                
            }
        }
        combi(0,0);
        return answer;
    }
    static void combi(int cnt, int start){
        if(cnt == bCnt){
            boolean[] can = new boolean[bCnt];
            for(int i = 0; i < bCnt; i++){
                for(Integer cur : banned[i]){
                    if( cur == list[i]){
                        can[i] = true;
                    }
                }
            }
            for(int i = 0; i < bCnt; i++){
                if(!can[i]){
                    return;
                }
            }
            answer++;
            return;
        }
        for(int i = start; i < uCnt; i++){
            list[cnt] = i;
            combi(cnt+1, i+1);
            list[cnt] = 0;
        }
    }
}
