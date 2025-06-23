import java.io.*;
import java.util.*;

class BOJ_4386_별자리만들기 {

    static class Edge implements Comparable<Edge> {
        int u, v;
        double cost;

        Edge(int u, int v, double cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.cost, other.cost);
        }
    }
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        double[][] stars = new double[n][2];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            stars[i][0] = Double.parseDouble(line[0]);
            stars[i][1] = Double.parseDouble(line[1]);
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dx = stars[i][0] - stars[j][0];
                double dy = stars[i][1] - stars[j][1];
                double dist = Math.sqrt(dx * dx + dy * dy);
                edges.add(new Edge(i, j, dist));
            }
        }

        Collections.sort(edges);
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }

        double tot = 0;
        int cnt = 0;

        for (Edge edge : edges) {
            int fx = findSet(edge.u);
            int fy = findSet(edge.v);

            if (fx != fy) {
                union(fx, fy);
                tot += edge.cost;
                cnt++;
                if (cnt == n - 1) break;
            }
        }

        System.out.printf("%.2f\n", tot);
    }

    static int findSet(int x) {
        if (p[x] != x) {
            p[x] = findSet(p[x]);
        }
        return p[x];
    }

    static void union(int x, int y) {
        p[y] = x;
    }
}