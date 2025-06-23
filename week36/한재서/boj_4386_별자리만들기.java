import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        double[][] stars = new double[n][2];
        for(int i=0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        int[] parent = new int[n];
        boolean[] visited = new boolean[n];

        for(int i=0; i < n; i++)
            parent[i] = i;

        ArrayList<double[]>[] adj = new ArrayList[n];
        for(int i=0; i < n; i++)
            adj[i] = new ArrayList<>();

        for(int i=0; i < n; i++) {
            for(int j=0; j < n; j++) {
                if(j != i) {
                    adj[i].add(new double[] {j, 
                        Math.sqrt((stars[i][0] - stars[j][0]) * (stars[i][0] - stars[j][0]) +
                        (stars[i][1] - stars[j][1]) * (stars[i][1] - stars[j][1]))
                    });
                }
            }
        }

        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] d1, double[] d2) {
                return Double.compare(d1[1], d2[1]);
            }
        });

        double minSum = 0;
        int cnt = 0;
        visited[0] = true;
        pq.addAll(adj[0]);
        while(!pq.isEmpty()) {
            double[] cur = pq.poll();
            if(visited[(int)cur[0]])
                continue;

            visited[(int)cur[0]] = true;
            minSum += cur[1];
            pq.addAll(adj[(int)cur[0]]);
            cnt++;
            if(cnt == n - 1)
                break;
        }

        System.out.printf("%.2f", minSum);
    }
}
