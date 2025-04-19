import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> users = new HashMap<>();
        String[] answer;
        String[] messages = new String[record.length];
        String[] orders = new String[record.length];
        int size = 0;
        int idx = 0;
        String[] message;
        for(int i=0; i < record.length; i++) {
            message = record[i].split(" ");
            String act = message[0];
            String userId = message[1];
            String nickname = message.length < 3 ? "" : message[2];
            switch(act) {
                case "Enter":
                    users.put(userId, nickname);
                    orders[i] = userId;
                    messages[i] = "님이 들어왔습니다.";
                    break;
                case "Leave":
                    orders[i] = userId;
                    messages[i] = "님이 나갔습니다.";
                    break;
                case "Change":
                    orders[i] = "";
                    users.put(userId, nickname);
                    size++;
                    break;
            }
        }
        answer = new String[record.length - size];
        for(int i=0; i < record.length; i++) {
            if(!orders[i].equals("")) answer[idx++] = users.get(orders[i]).concat(messages[i]);
        }
        return answer;
    }
}
