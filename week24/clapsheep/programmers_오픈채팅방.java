import java.util.*;
import java.io.*;
class Solution {
    static String eM = "님이 들어왔습니다.";
    static String lM = "님이 나갔습니다.";
    
    public String[] solution(String[] record) {
        int cnt = 0;
        Map<String, String> idName = new HashMap<>();

        for(String r : record){
            StringTokenizer st = new StringTokenizer(r);
            String action = st.nextToken();
            String id =  st.nextToken();
            
            if(!action.equals("Leave")){
                String name = st.nextToken();
                idName.put(id, name);
            }
            if(!action.equals("Change")){
                cnt++;
            }
            
        }
        String[] answer = new String[cnt];
        int idx = 0;
        for(String r : record){
             StringTokenizer st = new StringTokenizer(r);
            String action = st.nextToken();
            String id = st.nextToken();
            
            if (action.equals("Enter")) {
                answer[idx++] = idName.get(id) + eM;
            } else if (action.equals("Leave")) {
                answer[idx++] = idName.get(id) + lM;
            }
        }
        return answer;
    }
}
