import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Edge implements Comparable<Edge> {
        int n1, n2;
        double l;
        Edge(int n1, int n2, double l){
            this.n1 = n1;
            this.n2 = n2;
            this.l = l;
        }

        @Override
		public int compareTo(Edge o) {
			return Double.compare(this.l, o.l);
		}
    }
    static int n, m;
    static int[][] god;
    static int[] parent;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        god = new int[n][2];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            god[i][0] = Integer.parseInt(st.nextToken());
            god[i][1] = Integer.parseInt(st.nextToken());
        }

        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            union(a, b);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                double l = len(god[i], god[j]);
                pq.offer(new Edge(i, j, l));
            }
        }

        double result = 0;
        while(!pq.isEmpty()){
            Edge now = pq.poll();
            if(parent[now.n1] != parent[now.n2]){
                union(now.n1, now.n2);
                result += now.l;
            }
        }

        System.out.printf("%.2f%n", result);
        
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static double len(int[] a, int[] b) {
		return Math.sqrt(Math.pow(a[0]-b[0], 2) + Math.pow(a[1]-b[1], 2));
	}
}
