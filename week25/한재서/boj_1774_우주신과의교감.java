import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static class Connection implements Comparable<Connection> {
        int y;
        int x;
        double dis;

        Connection(int y, int x, double dis) {
            this.y = y;
            this.x = x;
            this.dis = dis;
        }

        @Override
        public int compareTo(Connection c1) {
            return Double.compare(dis, c1.dis);
        }
    }
    
    static int N, M, conCnt;
    static double ans;
    static int[] parent, rank;
    static int[][] idxs;
    static PriorityQueue<Connection> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        rank = new int[N];
        idxs = new int[N][2];
        ans = 0;
        conCnt = 0;
        pq = new PriorityQueue<>();

        for(int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            idxs[i][0] = Integer.parseInt(st.nextToken());
            idxs[i][1] = Integer.parseInt(st.nextToken());
            parent[i] = i;
            rank[i] = 1;
        }

        for(int i=0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        for(int i=0; i < N; i++) {
            for(int j=i + 1; j < N; j++) pq.offer(new Connection(i, j, distance(i, j)));
        }

        connect();
        System.out.printf("%.2f", ans);
    }

    static void connect() {
        while(!pq.isEmpty()) {
            Connection cur = pq.poll();
            if(union(cur.y, cur.x)) {
                ans += cur.dis;
                conCnt++;
            }
        }
    }

    static boolean union(int y, int x) {
        y = find(y);
        x = find(x);

        if(y == x) return false;

        if(rank[y] >= rank[x]) {
            rank[y] += rank[x];
            parent[x] = parent[y];
        } else {
            rank[x] += rank[y];
            parent[y] = parent[x];
        }

        return true;
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static double distance(int a, int b) {
        long deltaY = idxs[a][0] - idxs[b][0];
        long deltaX = idxs[a][1] - idxs[b][1];
        return Math.sqrt(deltaY * deltaY + deltaX * deltaX);
    }
}
