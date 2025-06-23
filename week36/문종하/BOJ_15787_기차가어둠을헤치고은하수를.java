import java.io.*;
import java.util.*;

public class BOJ_15787_기차가어둠을헤치고은하수를 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] train = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int x = Integer.parseInt(st.nextToken()) - 1;
                train[t] |= (1 << x);
            } else if (cmd == 2) {
                int x = Integer.parseInt(st.nextToken()) - 1;
                train[t] &= ~(1 << x);
            } else if (cmd == 3) {
                train[t] <<= 1;
                train[t] &= ((1 << 20) - 1);
            } else if (cmd == 4) {
                train[t] >>= 1;
            }
        }

        Set<Integer> states = new HashSet<>();

        for (int i = 1; i <= N; i++)
            states.add(train[i]);

        System.out.println(states.size());
    }
}