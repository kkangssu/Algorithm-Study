import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int V, E;
    static int[] r;
    static int[] p;
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

        r = new int[V+1];
        p = new int[V+1];
        
        for(int i = 1; i < V+1; i++){
            p[i] = i;
            r[i] = 0;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)-> a.w-b.w);
        for(int i = 0; i < E; i++){
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(a,b,w));
        }
        int cnt = 0;
        long res = 0;
        while(cnt != V-1){
            Edge cur = pq.poll();
            if(union(cur.a, cur.b)){
                res += cur.w;
                cnt++;
            }
        }
        System.out.println(res);
        

        
    }
    static int find (int v){
        if(p[v]== v){
            return v;
        }else{
            return p[v] = find(p[v]);
        }
    }
    static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b)return false;
        if(r[a] > r[b]){
            p[b] = a;
            r[a] += r[b];
        }else{
            p[a] = b;
            r[b] += r[a];
        }
        return true;
    }
}
