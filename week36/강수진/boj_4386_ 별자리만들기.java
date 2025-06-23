import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class XY{
        double x, y;
        XY(double x, double y){
            this.x = x;
            this.y = y;
        }
    }
    static class Edge implements Comparable<Edge>{
        int start, end;
        double cost;
        Edge(int start, int end, double cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
    		return Double.compare(this.cost, o.cost);
    	}
    }
    static List<Edge> edge;
    static int[] parent;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        XY[] star = new XY[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            star[i] = new XY(x, y);
        }

        edge = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for (int j = i+1; j < n; j++) {
                double cost = length(star[i], star[j]);
                edge.add(new Edge(i, j, cost));
            }
        }
        Collections.sort(edge);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        double result = 0;
        int cnt = 0;
        for(Edge e: edge){
            if(find(e.start) != find(e.end)){
                result += e.cost;
                union(e.start, e.end);
                if(++cnt == n-1) break;
            }
        }

        System.out.printf("%.2f\n", result);
    }

    static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b){
            parent[b] = a;
        }
    }

    static double length(XY s1, XY s2){
        return Math.sqrt(Math.pow(s1.x - s2.x, 2) + Math.pow(s1.y - s2.y, 2));
    }
}
