import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 간선 정보를 저장하는 클래스
class Edge {
    int dest;
    int weight;

    Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

// 우선순위 큐에서 사용할 노드 클래스
class Node implements Comparable<Node> {
    int vertex;
    int key;

    Node(int vertex, int key) {
        this.vertex = vertex;
        this.key = key;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.key, other.key);
    }
}

public class boj_1197_최소스패닝트리_프림 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());  // 정점의 개수
        int E = Integer.parseInt(st.nextToken());  // 간선의 개수

        // 인접 리스트 생성 (1-indexed)
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력받아 인접 리스트에 저장 (양방향)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        // 프림 알고리즘 수행 및 결과 출력
        System.out.println(prim(graph, V));
    }

    // 프림 알고리즘 구현
    public static int prim(List<List<Edge>> graph, int V) {
        // 각 정점의 키(최소 가중치) 값과 MST 포함 여부
        int[] key = new int[V + 1];
        boolean[] inMST = new boolean[V + 1];

        // 모든 정점의 키 값을 무한대로 초기화
        for (int i = 1; i <= V; i++) {
            key[i] = Integer.MAX_VALUE;
            inMST[i] = false;
        }

        // 시작 정점(1)의 키 값을 0으로 설정
        key[1] = 0;

        // 우선순위 큐 생성 (키 값이 작은 노드가 우선)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        int totalWeight = 0;

        while (!pq.isEmpty()) {
            // 키 값이 최소인 정점 선택
            int u = pq.poll().vertex;

            // 이미 MST에 포함된 정점은 처리하지 않음
            if (inMST[u]) continue;

            // 선택한 정점을 MST에 포함하고 가중치 합산
            inMST[u] = true;
            totalWeight += key[u];

            // 선택한 정점과 연결된 모든 이웃 정점 검사
            for (Edge edge : graph.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                // MST에 아직 포함되지 않았고, 새 경로의 가중치가 더 작은 경우 갱신
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    pq.add(new Node(v, weight));
                }
            }
        }

        return totalWeight;
    }
}
