import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static class Edge implements Comparable<Edge>{
        int pre, post, value;

        Edge(int pre, int post, int value) {
            this.pre = pre;
            this.post = post;
            this.value = value;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(value, e.value);
        }
    }
    
    static int[] parent, rank;
    static int V, E;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V];
        rank = new int[V];
        for(int i=0; i < V; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }

        int ans = 0; int cnt = 0;
        Edge cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            if(union(cur.pre, cur.post)) {
                ans += cur.value;
                cnt++;
            }

            if(cnt == E) break;
        }

        System.out.println(ans);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return false;

        if(rank[a] >= rank[b]) {
            rank[a] += rank[b];
            parent[b] = parent[a];
        } else {
            rank[b] += rank[a];
            parent[a] = parent[b];
        }

        return true;
    }

    static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}
