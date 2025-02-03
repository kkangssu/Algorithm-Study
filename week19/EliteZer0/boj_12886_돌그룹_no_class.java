import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 117632kb 212ms
public class boj_12886_돌그룹_no_class {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.print(bfs(A, B, C) ? 1 : 0);
    }

    private static boolean bfs(int a, int b, int c) {
        // 3개의 그룹의 돌의 개수가 같으려면 각 그룹의 합이 3으로 나누어 떨어져야 함.
        if ((a + b + c) % 3 != 0) return false;

        Queue<int[]> q = new ArrayDeque<>();
        // 한 돌 그룹이 가질 수 있는 돌의 개수는 0부터 최대 1500개까지
        // 세 수의 합이 항상 일정하므로, 두 수만 알면 나머지 하나는 자동으로 결정
        boolean[][] visited = new boolean[1501][1501];

        q.offer(new int[]{a, b, c});
        visited[a][b] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            int z = cur[2];

            if(x == y && y == z) return true;

            // 돌 그룹 쌍 간의 연산을 미리 정의
            int[][] pairs = {{x, y, z}, {x, z, y}, {y, z, x}};

            for(int[] pair : pairs) {
                int n1 = pair[0];
                int n2 = pair[1];
                int n3 = pair[2];

                if(n1 == n2) continue;

                int next1 = n1 > n2 ? n1-n2 : n1*2;
                int next2 = n1 > n2 ? n2*2 : n2-n1;

                if(!visited[next1][next2]) {
                    visited[next1][next2] = true;
                    q.offer(new int[]{next1, next2, n3});
                }
            }
        }
        return false;
    }
}