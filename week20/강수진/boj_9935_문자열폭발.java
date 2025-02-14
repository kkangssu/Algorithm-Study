package week20.강수진;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_9935_문자열폭발 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        String str = br.readLine();
        sb = new StringBuilder(br.readLine());
        String bomb = sb.reverse().toString();

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            st.push(str.charAt(i));
            if(st.size() >= bomb.length()) {
                boolean flag = true;
                for (int j = 0; j < bomb.length(); j++) {
                    if(st.get(st.size()-j-1) == bomb.charAt(j)) continue;
                    flag = false;
                    break;
                }
                if(flag) {
                    for (int j = 0; j < bomb.length(); j++) {
                        st.pop();
                    }
                }
            }
        }

        if(st.size() == 0) System.out.println("FRULA");
        else {
            sb = new StringBuilder();
            for (char c : st) {
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
}
