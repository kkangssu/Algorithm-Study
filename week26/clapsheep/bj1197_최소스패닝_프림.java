import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int V, E;
    
    static class Edge{
        int a, b, w;
        public Edge(int a, int b, int w){
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        List<Edge>[] adj = new ArrayList[V+1];
        for(int i = 1 ; i < V+1 ; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[a].add(new Edge(a,b,w));
            adj[b].add(new Edge(b,a,w));
        }
        
        long res = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)->a.w-b.w);
        boolean[] vis = new boolean[V+1];
        pq.addAll(adj[1]);
        int cnt = 1;
        vis[1] = true;
        while(cnt != V){
            Edge cur = pq.poll();
            if(vis[cur.b])continue;
            vis[cur.b] = true;
            res += cur.w;
            pq.addAll(adj[cur.b]);
            cnt++;
            
        }
        System.out.println(res);
    }
}
