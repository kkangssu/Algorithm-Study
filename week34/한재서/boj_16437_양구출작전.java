import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    // 트리 구조를 표현하기 위한 리스트 배열 (1번 노드부터 시작)
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    // 각 노드의 정보를 담을 info 배열 (각 노드의 값과 타입)
    static info[] infos;

    // info 클래스 : 각 노드의 타입(S, W)과 값 저장
    static class info {
        long value;    // 양/늑대의 개수
        boolean kinds; // true: 양(S), false: 늑대(W)

        public info(long value, boolean kinds) {
            this.kinds = kinds;
            this.value = value;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 노드 개수

        infos = new info[N + 1];
        for (int i = 0; i <= N; i++)
            tree.add(new ArrayList<>());

        StringTokenizer st;

        // 루트 노드(1번)는 항상 초기값으로 양 0마리
        infos[1] = new info(0, true);

        // 2번 노드부터 각 노드 정보 입력
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char t = st.nextToken().charAt(0);  // S 또는 W
            long a = Long.parseLong(st.nextToken());  // 양 또는 늑대의 수
            int p = Integer.parseInt(st.nextToken());  // 부모 노드 번호

            // 트리 연결 (부모 -> 자식)
            tree.get(p).add(i);

            // 노드 정보 저장
            infos[i] = new info(a, t == 'S');
        }

        // 루트 노드부터 탐색 시작 후 결과 출력
        System.out.println(search(1));
    }

    // DFS 탐색 함수
    static long search(int index) {
        long sum = 0;

        // 자식 노드 탐색 (재귀)
        for (int next : tree.get(index)) {
            sum += search(next);
        }

        // 현재 노드 처리
        if (infos[index].kinds) {  // 양 노드(S)
            return sum + infos[index].value;
        } else {  // 늑대 노드(W)
            return Math.max(0, sum - infos[index].value);
        }
    }
}
