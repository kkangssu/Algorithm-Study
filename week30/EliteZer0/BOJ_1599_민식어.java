import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] strs = new String[N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            String transformed = input.replace("k", "c").replace("ng", "n|");
            strs[i] = transformed;
        }

        Arrays.sort(strs);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String replaced = strs[i].replace("c", "k").replace("n|", "ng");
            sb.append(replaced).append("\n");
        }

        System.out.print(sb.toString());
    }
}
