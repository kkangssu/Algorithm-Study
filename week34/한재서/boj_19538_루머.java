import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    // 변수 선언
    static int N, M;
    static ArrayList<Integer>[] graph; // 그래프: 각 노드가 영향을 받는 노드 리스트
    static Queue<Integer> queue;       // BFS를 위한 큐
    static int[] time;                 // 각 노드가 감염되는데 걸리는 시간
    static int[] neighbor;             // 현재까지 감염된 이웃 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        time = new int[N + 1];
        neighbor = new int[N + 1];

        // 배열 초기화
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            time[i] = -1;  // -1: 아직 감염되지 않은 상태
        }

        // 그래프 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            while (true) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 0) break;  // 0 입력 시 종료
                graph[i].add(a);
            }
        }

        // 최초 감염된 사람 입력
        M = Integer.parseInt(br.readLine());
        queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int node = Integer.parseInt(st.nextToken());
            time[node] = 0;  // 처음 감염된 사람: 감염 시간 0
            queue.add(node); // BFS 시작점에 추가
        }

        bfs();  // BFS 실행

        // 출력
        for (int i = 1; i <= N; i++) {
            sb.append(time[i]);
            if (i != N) sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    // BFS 탐색
    public static void bfs() {
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                neighbor[next]++; // next 노드의 감염된 이웃 수 증가

                // 아직 감염되지 않았고, 전체 이웃 중 절반 이상이 감염되었을 경우 감염
                if (time[next] == -1 && (graph[next].size() + 1) / 2 <= neighbor[next]) {
                    queue.add(next);
                    time[next] = time[cur] + 1;  // 감염 시간 기록
                }
            }
        }
    }
}
