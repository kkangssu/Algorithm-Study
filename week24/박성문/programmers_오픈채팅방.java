import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        HashMap<String,String> hash = new HashMap<>();
        String[][] map = new String[record.length][2];
        
        for(int i = 0; i < record.length; i++) {
            String[] tmp  = record[i].split(" ");

            map[i][0] = tmp[0];
            map[i][1] = tmp[1];
            
            if(tmp.length == 3)
                hash.put(tmp[1], tmp[2]);
            
        }
        
        List<String> answer = new LinkedList<>();
        
        for(int i = 0; i < record.length; i++) {
            if(map[i][0].equals("Change"))
                continue;
            
            answer.add((map[i][0].equals("Enter")) ? 
                hash.get(map[i][1]) + "님이 들어왔습니다." :
                hash.get(map[i][1]) + "님이 나갔습니다.");    
        }
        
        return answer.toArray(new String[answer.size()]);
    }
}
