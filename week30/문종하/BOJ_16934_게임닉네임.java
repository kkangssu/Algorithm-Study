import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BOJ_16934_게임닉네임 {
    static class Node {
//        char cur;
//        int cnt;
        Node [] li = new Node [26];
    }
    static Node root = new Node();
    static Map<String, Integer> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++)
            add(br.readLine());

        System.out.println(sb);
    }
    static void add(String line){
        boolean flag = false; // 기존 존재 여부
        if(map.containsKey(line)){
            map.put(line, map.get(line)+1);
            sb.append(line).append(map.get(line)).append('\n');
            flag = true;
        }else
            map.put(line,1);

        char [] c = line.toCharArray();
        Node cur = root;
        for (int i = 0; i < c.length; i++) {
            int idx = c[i]-'a';
            if(cur.li[idx] == null){
                cur.li[idx] = new Node();
                if(!flag){
                    sb.append(String.valueOf(c, 0, i+1)).append('\n');
                    flag = true;
                }
//                cur.li[idx].cur = c[i];
            }
//            cur.li[idx].cnt++;
            cur = cur.li[idx];
        }
        if(!flag)
            sb.append(line).append('\n');
    }
}
