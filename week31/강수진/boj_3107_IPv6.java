import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String ip = br.readLine();
        ip = ip.replace("::", ":z:");
        StringTokenizer st = new StringTokenizer(ip, ":");
        String[] ips = new String[8];
        int idx = 0;
        while(st.hasMoreTokens()){
            ips[idx] = st.nextToken();
            idx++;
        }

        int zero = 9-idx;
        for(int i = 0; i < idx; i++){
            if(ips[i].equals("z")){
                for(int j = 0; j < zero; j++){
                    sb.append("0000:");
                }
            }
            else {
                sb.append(toIp(ips[i])).append(":");
            }
        }
        sb.setLength(39);
        System.out.println(sb);
    }

    static String toIp(String str){
        StringBuilder sb = new StringBuilder();
        int n = str.length();
        for(int i = 0; i < 4-n; i++){
            sb.append("0");
        }
        sb.append(str);
        return sb.toString();
    }
}
