import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static class Edge implements Comparable<Edge> {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(cost, o.cost);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        ArrayList<Edge>[] adj = new ArrayList[V];
        boolean[] visited = new boolean[V];
        for(int i=0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost));
        }

        int ans = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(visited[cur.to]) continue;
            visited[cur.to] = true;
            ans += cur.cost;
            for(Edge e : adj[cur.to]) {
                if(!visited[e.to]) pq.offer(e);
            }
        }

        System.out.println(ans);
    }
}
