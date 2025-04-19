import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_1197_최소스패닝트리_프림 {
    
    static int ans, V, E;
    static int[] p;
    static int[] dist;
    static final int INF = 987654321;
    static List<Edge>[] graph;
    static boolean [] vst;
    static class Edge implements Comparable<Edge> {
        int a,w;
        public Edge(int aa, int ww){
            this.a = aa;
            this.w = ww;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w-o.w;
        }
    }
    public static void main(String[] args) throws IOException {
        V = readInt();
        E = readInt();

        vst = new boolean[V+1];
        graph = new ArrayList[V+1];

        for (int i = 1; i <= V; i++)
            graph[i] = new ArrayList<>();

        p = new int[V+1];
//        Arrays.fill(p,-1);

        dist = new int[V+1];
        Arrays.fill(dist,INF);

        for (int i = 0; i < E; i++) {
            int a = readInt();
            int b = readInt();
            int w = readInt();
            graph[a].add(new Edge(b,w));
            graph[b].add(new Edge(a,w));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.offer(new Edge(1,0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            int curV = cur.a;
            if(vst[curV]) continue;
            vst[curV] = true;
            ans+=cur.w;
            for(Edge adjE : graph[curV]){
                if(vst[adjE.a] || dist[adjE.a] <= adjE.w)continue;
                pq.offer(adjE);
            }
        }

        System.out.println(ans);
    }
    static int readInt() throws IOException {
        int result = 0;
        int read = System.in.read();
        boolean isMinus = false;
        while(read > '9' || read < '0') {
            if(read=='-')isMinus=true;
            read = System.in.read();
        }
        while(read >= '0' && read <= '9') {
            result = result * 10 + read-'0';
            read = System.in.read();
        }
        return isMinus?-result:result;
    }
}
