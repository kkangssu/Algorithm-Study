import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
  
    static class Node implements Comparable<Node> {
        int idx, cost;
        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static int n, e;
    static List<Node>[] list;
    static int[][] dist;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        list = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        dist = new int[3][n+1];
        for(int i = 0; i < 3; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        dijkstra(1, 0);
        dijkstra(v1, 1);
        dijkstra(v2, 2);
        
        int path1 = 0;
        int path2 = 0;
        
        if (dist[0][v1] != Integer.MAX_VALUE && dist[1][v2] != Integer.MAX_VALUE && dist[2][n] != Integer.MAX_VALUE) {
            path1 = dist[0][v1] + dist[1][v2] + dist[2][n];
        } else {
            path1 = Integer.MAX_VALUE;
        }
        
        if (dist[0][v2] != Integer.MAX_VALUE && dist[2][v1] != Integer.MAX_VALUE && dist[1][n] != Integer.MAX_VALUE) {
            path2 = dist[0][v2] + dist[2][v1] + dist[1][n];
        } else {
            path2 = Integer.MAX_VALUE;
        }
        
        int result = Math.min(path1, path2);
        
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
    
    static void dijkstra(int start, int arrIdx){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[arrIdx][start] = 0;
        visited = new boolean[n+1];
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(visited[now.idx]) continue;
            visited[now.idx] = true;
            for(Node next: list[now.idx]){
                if(visited[next.idx]) continue;
                if(dist[arrIdx][next.idx] <= now.cost + next.cost) continue;
                dist[arrIdx][next.idx] = now.cost + next.cost;
                pq.add(new Node(next.idx, dist[arrIdx][next.idx]));
            }
        }
    }    
}
