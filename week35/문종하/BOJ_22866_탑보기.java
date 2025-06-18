import java.util.*;
import java.io.*;

public class BOJ_22866_탑보기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            h[i] = Integer.parseInt(st.nextToken());

        int[] lc = new int[n];
        int[] rc = new int[n];
        int[] ln = new int[n];
        int[] rn = new int[n];

        Arrays.fill(ln, -1);
        Arrays.fill(rn, -1);

        // 왼쪽
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && h[s.peek()] <= h[i])
                s.pop();

            lc[i] = s.size();
            if (!s.isEmpty())
                ln[i] = s.peek();

            s.push(i);
        }

        // 오른쪽
        s.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && h[s.peek()] <= h[i])
                s.pop();

            rc[i] = s.size();
            if (!s.isEmpty())
                rn[i] = s.peek();

            s.push(i);
        }

        for (int i = 0; i < n; i++) {
            int tot = lc[i] + rc[i];

            if (tot == 0) {
                sb.append("0\n");
            } else {
                int near = -1;
                int min = Integer.MAX_VALUE;

                if (ln[i] != -1) {
                    int d = i - ln[i];
                    if (d < min) {
                        min = d;
                        near = ln[i] + 1;
                    }
                }

                if (rn[i] != -1) {
                    int d = rn[i] - i;
                    if (d < min || (d == min && rn[i] + 1 < near)) {
                        min = d;
                        near = rn[i] + 1;
                    }
                }

                sb.append(tot).append(" ").append(near).append("\n");
            }
        }

        System.out.print(sb);
    }
}