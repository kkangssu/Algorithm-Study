import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, E, v1,v2;
    static class Node implements Comparable<Node>{
        int v, w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Node o){
            return this.w-o.w;
        }
    }
    static List<Node>[] adj;
    static int INF = Integer.MAX_VALUE;
    static int s1,s2,middle,e1,e2;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        for(int i = 0; i < N+1 ; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < E ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b,w));
            adj[b].add(new Node(a,w));
        }
        st= new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        
       // int res = INF;
       // int a = djikstra(1,v1);
       // int b = djikstra(v1,v2);
       // int c = djikstra(v2,N);
       // if(a !=INF && b!=INF && c!=INF){
       //     res = Math.min(res, a+b+c);
       // }
       // a = djikstra(1,v2);
       // b = djikstra(v2,v1);
       // c = djikstra(v1,N);
       // if(a !=INF && b!=INF && c!=INF){
       //     res = Math.min(res, a+b+c);
       // }
       // System.out.println(res != INF ? res :-1);
        middle = djikstra(v1,v2);
        if(middle == INF){
            System.out.println(-1);
            return;
        }
        
        djikstra(1);
        djikstra(N);
        int case1 = INF;
        if(s1 != INF && e1 != INF){
            case1 = Math.min(case1, s1 + middle + e1);
        }
        
        int case2 = INF;
        if(s2 != INF && e2 != INF){
            case2 = Math.min(case2, s2 + middle + e2);
        }
        int res = Math.min(case1,case2);
        System.out.println(res == INF ? -1 : res);
}
        static int djikstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        boolean[] vis = new boolean[N+1];
        dist[start] = 0;
        pq.offer(new Node(start,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(vis[cur.v]) continue;
            vis[cur.v] = true;
            for(Node n : adj[cur.v]){
                if(!vis[n.v] && dist[n.v] > dist[cur.v]+n.w){
                    dist[n.v] = dist[cur.v]+n.w;
                    pq.offer(new Node(n.v, dist[n.v]));
                }
            }
        }
        return dist[end];
    }

        static void djikstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        boolean[] vis = new boolean[N+1];
        dist[start] = 0;
        pq.offer(new Node(start,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(vis[cur.v]) continue;
            vis[cur.v] = true;
            for(Node n : adj[cur.v]){
                if(!vis[n.v] && dist[n.v] > dist[cur.v]+n.w){
                    dist[n.v] = dist[cur.v]+n.w;
                    pq.offer(new Node(n.v, dist[n.v]));
                }
            }
        }
        if(start == 1){
            s1 = dist[v1];
            s2 = dist[v2];
        }else{
            e1 = dist[v2];
            e2 = dist[v1];
        }
    }
}
