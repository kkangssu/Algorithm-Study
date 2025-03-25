import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_24542 {
    static int[] parent;

    // 초기화 메서드로 분리했을 땐 75708kb 428ms 77732kb 432ms
    // main 안에서 처리하면 78164kb 540ms 82488kb 540ms
    // 원인을 찾지 못하였습니다...
    static void init(int n) {
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        init(n);

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        // Map을 사용하여 각 연결 요소(집합)의 루트 노드를 키로, 해당 집합의 크기를 값으로 저장
        // 배열 대신 Map을 사용한 이유:
        // 루트 노드의 번호를 예측할 수 없음
        // Map을 사용하면 실제 존재하는 루트 노드만 저장
        // getOrDefault 메서드를 통해 값이 없을 경우 기본값(0) 반환 가능
        Map<Integer, Integer> sizeMap = new HashMap<>();

        // 각 노드를 순회하며 자신이 속한 집합(연결 요소)의 루트를 찾고, 해당 집합의 크기를 1 증가
        for (int i = 1; i <= n; i++) {
            int root = find(i);
            sizeMap.put(root, sizeMap.getOrDefault(root, 0) + 1);
        }

        long result = 1;
        final int MOD = 1_000_000_007;

        // 매 연산마다 MOD 값으로 안 나누면 쓰레기 값 나올 수도 있음
        for (int size : sizeMap.values()) {
            result = (result * size) % MOD;
        }

        System.out.println(result);
    }
}
