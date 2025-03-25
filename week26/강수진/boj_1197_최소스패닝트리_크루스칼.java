import java.util.*;
import java.io.*;

// 크루스칼 알고리즘을 사용하여 최소 스패닝 트리 구하기
public class Main {
    // 간선 정보를 저장하는 클래스
    static class Edge implements Comparable<Edge> {
        int from;   // 시작 정점
        int to;     // 도착 정점
        int cost; // 가중치
        
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        
        // 간선을 가중치 기준으로 오름차순 정렬하기 위한 비교 메서드
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    
    // Union-Find를 위한 부모 배열
    static int[] parent;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int v = Integer.parseInt(st.nextToken()); // 정점의 개수
        int e = Integer.parseInt(st.nextToken()); // 간선의 개수
        
        // 모든 간선 정보를 저장할 리스트
        List<Edge> list = new ArrayList<>();
        
        // 간선 정보 입력
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 정점
            int b = Integer.parseInt(st.nextToken()); // 도착 정점
            int c = Integer.parseInt(st.nextToken()); // 가중치
            
            list.add(new Edge(a, b, c));
        }
        
        // 간선을 가중치 기준으로 오름차순 정렬
        Collections.sort(list);
        
        // Union-Find 초기화: 각 정점을 독립적인 집합으로 설정
        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i; // 자기 자신을 부모로 초기화
        }
        
        // 크루스칼 알고리즘 수행
        long sumCost = 0; // MST의 총 가중치
        int cnt = 0;    // 선택된 간선의 개수
        
        for (Edge edge : list) {
            // 두 정점이 서로 다른 집합에 속한 경우에만 간선 선택 (사이클 방지)
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to); // 두 정점의 집합 합치기
                sumcost += edge.cost; // 가중치 합산
                cnt++;
                
                // V-1개의 간선이 선택되면 MST 완성 (종료 조건)
                if (cnt == v - 1) {
                    break;
                }
            }
        }
        
        // 최소 스패닝 트리의 가중치 출력
        System.out.println(sumCost);
    }
    
    // Find 연산: 정점이 속한 집합의 대표 원소(루트)를 찾음
    static int find(int x) {
        if (parent[x] == x) {
            return x; // 자기 자신이 루트인 경우
        }
        // 경로 압축(Path Compression) 최적화
        return parent[x] = find(parent[x]);
    }
    
    // Union 연산: 두 집합을 하나로 합침
    static void union(int x, int y) {
        x = find(x); // x의 루트 찾기
        y = find(y); // y의 루트 찾기
        
        if (x != y) {
            parent[y] = x; // y의 루트를 x의 루트로 설정
        }
    }
}
