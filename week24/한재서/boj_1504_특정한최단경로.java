import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class Main {
    static ArrayList<int[]>[] edges;
    static int[] costs;
    static boolean[] visited;
    static PriorityQueue<int[]> pq;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N];
        costs = new int[N];
        visited = new boolean[N];
        for(int i=0; i < N; i++) {
            edges[i] = new ArrayList<int[]>();
        }

        int a, b, c;
        for(int i=0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());
            edges[a].add(new int[] {b, c});
            edges[b].add(new int[] {a, c});
        }

        st = new StringTokenizer(br.readLine());
        int target1 = Integer.parseInt(st.nextToken()) - 1;
        int target2 = Integer.parseInt(st.nextToken()) - 1;

        long cost1 = 0L;
        long cost2 = 0L;
        cost1 += dijkstra(0, target1);
        cost1 += dijkstra(target1, target2);
        cost1 += dijkstra(target2, N - 1);
        cost2 += dijkstra(0, target2);
        cost2 += dijkstra(target2, target1);
        cost2 += dijkstra(target1, N - 1);

        long ans = cost1 < cost2 ? cost1 : cost2;
        if(ans >= Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    static int dijkstra(int from, int to) {
        Arrays.fill(costs, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[] {from, 0});
        costs[from] = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(visited[cur[0]]) continue;

            visited[cur[0]] = true;
            for(int i=0; i < edges[cur[0]].size(); i++) {
                int[] next = edges[cur[0]].get(i);

                if(costs[next[0]] > costs[cur[0]]+next[1]){
                    costs[next[0]] = costs[cur[0]] + next[1];
                    pq.offer(new int[] {next[0], costs[next[0]]});
                }
            }
        }

        return costs[to];
    }
}
