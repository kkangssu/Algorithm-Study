import java.io.*;
import java.util.*;

public class BOJ_1325_효율적인해킹 {
    static int n, m;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new List[n + 1];
        for (int i = 1; i <= n; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[b].add(a);
        }

        int max = 0;
        int[] cnt = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            cnt[i] = bfs(i);
            max = Math.max(max, cnt[i]);
        }

        for (int i = 1; i <= n; i++)
            if (cnt[i] == max)
                sb.append(i).append(" ");

        System.out.println(sb);
    }

    static int bfs(int start) {
        boolean[] v = new boolean[n + 1];
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(start);
        v[start] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adj[cur]) {
                if (!v[next]) {
                    v[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}