import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] parent, rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        rank = new int[N];
        long ans = 1L;

        for(int i=0; i < N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for(int i=0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        for(int i=0; i < N; i++) {
            ans *= rank[i];
            ans %= 1000000007;
        }

        System.out.println(ans);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return;
        
        if(rank[a] >= rank[b]) {
            parent[b] = parent[a];
            rank[a] += rank[b];
            rank[b] = 1;
        } else {
            parent[a] = parent[b];
            rank[b] += rank[a];
            rank[a] = 1;
        }
    }

    static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}
