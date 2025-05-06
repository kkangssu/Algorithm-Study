import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        String[] strs = new String[n];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            str = str.replaceAll("k", "c");
            str = str.replaceAll("p", "q");
            str = str.replaceAll("o", "p");
            str = str.replaceAll("ng", "o");
            
            strs[i] = str;
        }

        Arrays.sort(strs);

        for(int i = 0; i < n; i++){
            String str = strs[i];
            str = str.replaceAll("c", "k");
            str = str.replaceAll("o", "ng");
            str = str.replaceAll("p", "o");
            str = str.replaceAll("q", "p");
            
            sb.append(str).append("\n");
        }
        System.out.println(sb);
    }
}
