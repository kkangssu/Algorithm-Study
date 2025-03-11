import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    
    static class Command{
        int comm;
        String user;
        Command(int comm, String user){
            this.comm = comm;
            this.user = user;
        }
    }
    
    public String[] solution(String[] record) {
        StringBuilder sb;
        Map<String, String> map = new HashMap<>();
        List<Command> list = new ArrayList<>();
        for(int i = 0; i < record.length; i++){
            StringTokenizer st = new StringTokenizer(record[i]);
            String comm = st.nextToken();
            if(comm.equals("Change")){
                map.put(st.nextToken(), st.nextToken());
            }
            else if(comm.equals("Enter")){
                String user = st.nextToken();
                map.put(user, st.nextToken());
                list.add(new Command(1, user));
            }
            else{
                list.add(new Command(2, st.nextToken()));
            }
        }

        String[] result = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            sb = new StringBuilder();
            if(list.get(i).comm == 1){
                sb.append(map.get(list.get(i).user)).append("님이 들어왔습니다.");
            }
            else{
                sb.append(map.get(list.get(i).user)).append("님이 나갔습니다.");
            }
            result[i] = sb.toString();
        }
        return result;
    }
}

