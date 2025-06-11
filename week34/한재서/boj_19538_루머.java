import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static Queue<Integer> queue;
    static int[] time;
    static int[] neighbor;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        time = new int[N + 1];
        neighbor = new int[N + 1];
        for(int i=1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            time[i] = -1;
        }

        for(int i=1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            while(true) {
                int a = Integer.parseInt(st.nextToken());
                if(a == 0) break;
                graph[i].add(a);
            }
        }

        M = Integer.parseInt(br.readLine());
        queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < M; i++) {
            int node = Integer.parseInt(st.nextToken());
            time[node] = 0;
            queue.add(node);
        }

        bfs();
        for(int i=1; i <= N; i++) {
            sb.append(time[i]);
            if(i != N) sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void bfs() {
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int next: graph[cur]) {
                neighbor[next]++;

                if(time[next] == -1 && (graph[next].size() + 1) / 2 <= neighbor[next]) {
                    queue.add(next);
                    time[next] = time[cur] + 1;
                }
            }
        }
    }
}
