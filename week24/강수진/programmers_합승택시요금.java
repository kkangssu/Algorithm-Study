import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    
    static class Node implements Comparable<Node>{
        int idx;
        int cost;
        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
		    return Integer.compare(this.cost, o.cost);
	    }
    }
    static List<Node>[] graph;
    static boolean[] visited;
    static int[][] dist;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
        for(int i = 0; i < fares.length; i++){
            int n1 = fares[i][0];
            int n2 = fares[i][1];
            int c = fares[i][2];
            graph[n1].add(new Node(n2, c));
            graph[n2].add(new Node(n1, c));
        }

        
        dist = new int[3][n+1];
        for(int i = 0; i < 3; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dijkstra(s, 0, n);
        dijkstra(a, 1, n);
        dijkstra(b, 2, n);
        
        int minCost = Integer.MAX_VALUE;

        for(int i = 0; i <= n; i++){
            if(dist[0][i] == Integer.MAX_VALUE) continue;
            if(dist[1][i] == Integer.MAX_VALUE) continue;
            if(dist[2][i] == Integer.MAX_VALUE) continue;
            int cost = dist[0][i] + dist[1][i] + dist[2][i];
            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    static void dijkstra(int start, int arrIdx, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[arrIdx][start] = 0;
        visited = new boolean[n+1];
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(visited[now.idx]) continue;
            visited[now.idx] = true;
            for(Node next: graph[now.idx]){
                if(visited[next.idx])continue;
                if(dist[arrIdx][next.idx] <= now.cost + next.cost) continue;
                dist[arrIdx][next.idx] = now.cost + next.cost;
                pq.add(new Node(next.idx, dist[arrIdx][next.idx]));
            }
        }
    }
}
