import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 간선 클래스 정의
class Edge implements Comparable<Edge> {
    int from, to, weight;  // 시작점, 도착점, 가중치

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // 가중치 기준으로 오름차순 정렬
    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

public class boj_1197_최소스패닝트리_크루스칼 {
    // Union-Find를 위한 부모 배열과 간선을 저장할 우선순위 큐
    static int[] parent;  // 각 정점의 부모를 저장하는 배열
    static PriorityQueue<Edge> pq;  // 간선을 가중치 순으로 정렬할 우선순위 큐

    // Find 연산: 정점의 루트 노드를 찾음 (경로 압축 최적화 포함)
    static int find(int x) {
        if (parent[x] == x) return x;  // 자신이 루트면 자신을 반환
        return parent[x] = find(parent[x]);  // 경로 압축
    }

    // Union 연산: 두 정점을 연결
    static void union(int x, int y) {
        x = find(x);  // x의 루트 찾기
        y = find(y);  // y의 루트 찾기
        // 더 작은 값을 가진 정점이 부모가 되도록 함
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());  // 정점의 개수
        int E = Integer.parseInt(st.nextToken());  // 간선의 개수

        // 자료구조 초기화
        parent = new int[V+1];
        pq = new PriorityQueue<>();

        // 부모 배열 초기화 (처음에는 자기 자신이 부모)
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // 간선 정보 입력받아 우선순위 큐에 저장
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(from, to, weight));
        }

        // 최소 신장 트리의 총 가중치 출력
        System.out.println(kruskal(V));
    }

    // 크루스칼 알고리즘 구현
    public static int kruskal(int V) {
        int cost = 0;  // 최소 신장 트리의 총 가중치
        int edges = 0;  // 선택된 간선의 개수

        // 가중치가 작은 간선부터 처리
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();  // 가장 가중치가 작은 간선 선택

            // 사이클이 형성되지 않는 경우에만 간선 선택
            if (find(cur.from) != find(cur.to)) {
                union(cur.from, cur.to);  // 두 정점 연결
                cost += cur.weight;  // 가중치 추가
                edges++;  // 선택된 간선 수 증가
            }

            // V-1개의 간선이 선택되면 종료 (최소 신장 트리 완성)
            if (edges == V-1) break;
        }

        return cost;
    }
}
