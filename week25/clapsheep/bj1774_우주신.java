import java.util.*;
import java.lang.*;
import java.io.*;
// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static God[] list;
    static class God{
        int x, y;
        public God(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static class Edge implements Comparable<Edge> {
        int a, b;
        double w;
        public Edge(int a, int b, double w){
            this.a = a;
            this.b = b;
            this.w = w;
        }
        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.w, e.w);
        }
    }
    static int[] p, r;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new God[N];
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[i] = new God(x, y);
        }
        
        r = new int[N];
        p = new int[N];
        for(int i = 0; i < N; i++){
            r[i] = 1;
            p[i] = i;
        }
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            union(s, e);
        }
        
        // 크루스칼
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0; i < N - 1; i++){
            for(int j = i + 1; j < N; j++){
                pq.offer(new Edge(i, j, calcWeight(list[i], list[j])));
            }
        }
        
        double res = 0.0;
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(union(cur.a, cur.b)){
                res += cur.w;
            }
        }
        
        System.out.println(String.format("%.2f", res));
    }
    
    public static double calcWeight(God a, God b){
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    
    public static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b){
            if(r[a] > r[b]){
                r[a] += r[b];
                p[b] = a;
            }else{
                r[b] += r[a];
                p[a] = b;
            }
            return true;
        }
        return false;
    }
    
    public static int find(int v){
        if(v == p[v]){
            return v;
        }else{
            return p[v] = find(p[v]);
        }
    }
}
