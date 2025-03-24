import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_24542 {
    static int[] parent;

    static void init(int n) {
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        init(n);

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        Map<Integer, Integer> sizeMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            int root = find(i);
            sizeMap.put(root, sizeMap.getOrDefault(root, 0) + 1);
        }

        long result = 1;
        final int MOD = 1_000_000_007;

        for (int size : sizeMap.values()) {
            result = (result * size) % MOD;
        }

        System.out.println(result);
    }
}
