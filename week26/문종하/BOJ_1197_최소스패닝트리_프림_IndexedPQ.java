import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    PriorityQueue 최적화를 위한 IndexedPriorityQueue 구현 방식
    304ms -> 212ms
*/
class BOJ_1197_최소스패닝트리_프림_IndexedPQ {

    static int ans, V, E;
    static List<Edge>[] graph;
    static boolean[] vst;

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // IndexedPriorityQueue 구현
    static class IndexedPriorityQueue {
        private int[] heap;       // 힙 배열
        private int[] pos;        // 각 정점의 힙에서의 위치
        private int[] keys;       // 각 정점의 키(거리) 값
        private int size;         // 현재 힙 크기

        public IndexedPriorityQueue(int capacity) {
            heap = new int[capacity];
            pos = new int[capacity];
            keys = new int[capacity];
            size = 0;

            Arrays.fill(pos, -1);  // 초기에는 모든 정점이 힙에 없음
        }

        // 힙이 비었는지 확인
        public boolean isEmpty() {
            return size == 0;
        }

        // 정점이 힙에 있는지 확인
        public boolean contains(int v) {
            return pos[v] != -1;
        }

        // 힙에 정점 삽입 또는 키 값 갱신
        public void offer(int v, int key) {
            if (!contains(v)) {
                // 새로운 정점 삽입
                heap[size] = v;
                pos[v] = size;
                keys[v] = key;
                size++;
                siftUp(pos[v]);
            } else if (key < keys[v]) {
                // 키 값이 더 작으면 갱신
                keys[v] = key;
                siftUp(pos[v]);
            }
        }

        // 최소 키 값을 가진 정점 추출
        public int poll() {
            if (isEmpty()) {
                throw new RuntimeException("Priority queue underflow");
            }

            int min = heap[0];
            swap(0, size - 1);
            size--;
            pos[min] = -1;

            if (size > 0) {
                siftDown(0);
            }

            return min;
        }

        // 상향 조정
        private void siftUp(int k) {
            while (k > 0) {
                int parent = (k - 1) / 2;
                if (keys[heap[k]] >= keys[heap[parent]]) {
                    break;
                }
                swap(k, parent);
                k = parent;
            }
        }

        // 하향 조정
        private void siftDown(int k) {
            int half = size / 2;
            while (k < half) {
                int child = 2 * k + 1;
                if (child + 1 < size && keys[heap[child + 1]] < keys[heap[child]]) {
                    child++;
                }

                if (keys[heap[k]] <= keys[heap[child]]) {
                    break;
                }

                swap(k, child);
                k = child;
            }
        }

        // 두 위치의 요소 교환
        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;

            pos[heap[i]] = i;
            pos[heap[j]] = j;
        }
    }

    public static void main(String[] args) throws IOException {
        V = readInt();
        E = readInt();

        vst = new boolean[V + 1];
        graph = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int a = readInt();
            int b = readInt();
            int w = readInt();
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }

        // 프림 알고리즘 (IndexedPriorityQueue 사용)
        IndexedPriorityQueue pq = new IndexedPriorityQueue(V + 1);
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 시작 정점 1로 설정
        dist[1] = 0;
        pq.offer(1, 0);

        while (!pq.isEmpty()) {
            int curr = pq.poll();

            if (vst[curr]) continue;
            vst[curr] = true;
            ans += dist[curr];

            for (Edge edge : graph[curr]) {
                int next = edge.to;
                int weight = edge.weight;

                if (!vst[next] && weight < dist[next]) {
                    dist[next] = weight;
                    pq.offer(next, weight);
                }
            }
        }

        System.out.println(ans);
    }

    static int readInt() throws IOException {
        int result = 0;
        int read = System.in.read();
        boolean isMinus = false;
        while (read > '9' || read < '0') {
            if (read == '-') isMinus = true;
            read = System.in.read();
        }
        while (read >= '0' && read <= '9') {
            result = result * 10 + read - '0';
            read = System.in.read();
        }
        return isMinus ? -result : result;
    }
}