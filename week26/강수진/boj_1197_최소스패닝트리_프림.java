import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 간선 정보를 저장하는 클래스
    // 프림 알고리즘에서는 '도착 노드'와 '가중치'가 필요함
    static class Edge implements Comparable<Edge> {
        int node; // 도착 노드 번호
        int cost; // 간선의 가중치
        
        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        
        // 우선순위 큐에서 가중치가 낮은 간선이 먼저 추출되도록 비교 메서드 구현
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    
    static int v, e; // v: 정점 수, e: 간선 수
    static List<Edge>[] list; // 인접 리스트: 각 정점에 연결된 간선들을 저장
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 정점 수와 간선 수 입력
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        
        // 인접 리스트 초기화 (1-indexed 사용)
        list = new ArrayList[v+1];
        for(int i = 0; i <= v; i++) {
            list[i] = new ArrayList<>();
        }
        
        // 간선 정보 입력 및 인접 리스트 구성
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 정점
            int b = Integer.parseInt(st.nextToken()); // 도착 정점
            int c = Integer.parseInt(st.nextToken()); // 가중치
            
            // 양방향 그래프이므로 양쪽 모두에 간선 정보 추가
            list[a].add(new Edge(b, c));
            list[b].add(new Edge(a, c));
        }
        
        // 1번 정점부터 시작하여 프림 알고리즘 수행 및 결과 출력
        System.out.println(prim(1));
    }
    
    // 프림 알고리즘 구현 메서드
    static int prim(int start) {
        // 각 정점의 MST 포함 여부를 저장하는 배열
        boolean[] visited = new boolean[v+1];
        
        // 최소 가중치 간선을 효율적으로 선택하기 위한 우선순위 큐
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        // 시작 정점을 큐에 삽입 (시작 정점은 가중치 0으로 설정)
        pq.offer(new Edge(start, 0));
        
        // MST의 총 가중치
        int sum = 0;
        
        // 우선순위 큐가 비어있지 않은 동안 반복
        while(!pq.isEmpty()) {
            // 가장 가중치가 작은 간선 추출
            Edge now = pq.poll();
            int node = now.node; // 현재 노드
            int cost = now.cost; // 현재 간선의 가중치
            
            // 이미 MST에 포함된 노드라면 건너뛰기 (사이클 방지)
            if(visited[node]) continue;
            
            // 현재 노드를 MST에 포함시키고 가중치 합산
            visited[node] = true;
            sum += cost;
            
            // 현재 노드와 연결된 모든 간선 검사
            for(Edge e: list[node]) {
                // 이미 MST에 포함된 노드로 향하는 간선은 건너뛰기
                if(visited[e.node]) continue;
                
                // 아직 방문하지 않은 노드로 향하는 간선을 우선순위 큐에
                // 삽입 (가중치가 작은 순으로 자동 정렬됨)
                pq.offer(e);
            }
        }
        
        // MST의 총 가중치 반환
        return sum;        
    }
}
