import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] parent, rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = new int[N];
        rank = new int[N];

        for(int i=0; i < N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        StringTokenizer st;
        for(int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < N; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] plan = new int[M];
        for(int i=0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken());

        boolean isPossible = true;
        for(int i=0; i < M - 1; i++) {
            if(find(plan[i] - 1) != find(plan[i + 1] - 1)) {
                isPossible = false;
                break;
            }
        }

        System.out.println(isPossible ? "YES" : "NO");
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(rank[x] < rank[y]) {
            rank[y] += rank[x];
            parent[x] = parent[y];
        } else {
            rank[x] += rank[y];
            parent[y] = parent[x];
        }
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }
}
