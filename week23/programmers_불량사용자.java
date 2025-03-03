import java.util.*;

class Solution {
    static int answer;
    static String[] bList;
    static int N;
    static List<String>[] li;
    static HashSet<String> hash;
    static HashSet<String> hash2;
    static String[] hashWordList;

    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        
        hash  = new HashSet<>();
        hash2  = new HashSet<>();
     
        N = banned_id.length;
        li = new ArrayList[N];
        bList = new String[N];
        hashWordList = new String[N];
        
        
        for(int i = 0; i < N; i++){
            li[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < N; i++){
            for(String user : user_id){
                if(banned_id[i].length() != user.length())
                    continue;
                boolean no = false;
                for(int j = 0; j < banned_id[i].length(); j++){
                    if(banned_id[i].charAt(j) != user.charAt(j) 
                       && banned_id[i].charAt(j) != '*'){
                        no = true;
                        break;
                    }
                }
                
                if(!no) {
                    li[i].add(user);
                }
            }
        }
        
        comb(0); 
        
        return answer;
    }
    
    static void comb(int cnt) {
        if(cnt==N){
            hash.clear();
            for(int i = 0; i < N; i++){
                if(hash.contains(bList[i])){
                    return;
                }
                hash.add(bList[i]);
                hashWordList[i] = bList[i];
            }
            
            Arrays.sort(hashWordList);
            
            String hashWord = "";
            
            for(String word: hashWordList){
                hashWord += word;
            }
            
            if(hash2.contains(hashWord))
                return;
            
            hash2.add(hashWord);
            
            answer++;
            return;
        }
        
        for(int i = 0; i < li[cnt].size(); i++){
            bList[cnt] = li[cnt].get(i);
            comb(cnt+1);
        }
        
        
    }
}
