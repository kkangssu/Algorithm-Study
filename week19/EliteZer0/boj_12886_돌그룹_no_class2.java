import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 27124kb 140ms
// 처음 클래스 없이 작성한 코드가 visted[a][b]만 체크해 중복 방문을 효과적으로 방지하지 못함
// pairs 배열을 매 반복마다 새로 생성해 메모리 낭비가 심함
// 모든 조합의 방문 상태를 체크하여 중복 방문을 더 효과적으로 방지함
// 단순 변수를 사용해 메모리 사용을 줄임
public class boj_12886_돌그룹_no_class2 {
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
        visited[a][c] = true;
        visited[b][c] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            a = cur[0];
            b = cur[1];
            c = cur[2];

            if(a == b && b == c) return true;

            if(a != b) {
                int na = a > b ? a-b : a*2;
                int nb = a > b ? b*2 : b-a;
                if(!visited[na][nb]) {
                    visited[na][nb] = true;
                    visited[na][c] = true;
                    visited[nb][c] = true;
                    q.offer(new int[]{na, nb, c});
                }
            }

            if(a != c) {
                int na = a > c ? a-c : a*2;
                int nc = a > c ? c*2 : c-a;
                if(!visited[na][nc]) {
                    visited[na][nc] = true;
                    visited[na][b] = true;
                    visited[b][nc] = true;
                    q.offer(new int[]{na, b, nc});
                }
            }

            if(b != c) {
                int nb = b > c ? b-c : b*2;
                int nc = b > c ? c*2 : c-b;
                if(!visited[nb][nc]) {
                    visited[nb][nc] = true;
                    visited[a][nb] = true;
                    visited[a][nc] = true;
                    q.offer(new int[]{a, nb, nc});
                }
            }
        }
        return false;
    }
}
