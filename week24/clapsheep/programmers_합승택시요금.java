import java.util.*;
class Solution {
    static class Node{
        int v,w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    static List<Node>[] adj;
    static final int INF = 9876543;
    static int[][] dist;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        adj = new ArrayList[n+1];
        for(int i = 0; i < n+1 ; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < fares.length; i++){
                int[] cur = fares[i];
                adj[cur[0]].add(new Node(cur[1],cur[2]));
                adj[cur[1]].add(new Node(cur[0],cur[2]));

        }
        dist = new int[n+1][n+1];
        for(int i = 0 ; i< n+1; i++){
            Arrays.fill(dist[i],INF);    
        }

        // s에서 전부
        // a에서 전부
        // b에서 전부
        djikstra(s,n);
        djikstra(a,n);
        djikstra(b,n);
        int A = dist[s][a];
        int B = dist[s][b];
        int C = dist[a][b];
        int answer = Math.min(A+C,B+C);
        answer = Math.min(answer,A+B);
        for(int i = 1 ; i < n+1; i++){
            int temp =dist[s][i];
            temp += dist[a][i];
            temp += dist[b][i];
            answer = Math.min(answer, temp);
        }
        return answer;
    }
    static void djikstra(int start, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{return a.w-b.w;});
        boolean[] vis = new boolean[n+1];

        pq.offer(new Node(start, 0));
        dist[start][start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(vis[cur.v])continue;
            vis[cur.v] = true;
            for(Node now : adj[cur.v]){
                if(!vis[now.v] && dist[start][now.v] > dist[start][cur.v] + now.w){
                    dist[start][now.v] = dist[start][cur.v] + now.w;
                    pq.offer(new Node(now.v, dist[start][cur.v]+now.w));
                }
            }
        }

    }
}
