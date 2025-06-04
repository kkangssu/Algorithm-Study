import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_20056_마법사상어와파이어볼 {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static class Node {
        int r, c, m, s, d;
        public Node(int r, int c, int m, int s, int d) {
            this.r = r; this.c = c; this.m = m; this.s = s; this.d = d;
        }
    }
    static int N, M, K;
    static Queue<Node> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            q.offer(new Node(r, c, m, s, d));
        }

        System.out.println(bfs());
    }

    static int bfs() {
        for (int turn = 0; turn < K; turn++) {
            List<Node>[][] board = new List[N][N];
            Queue<Integer> qq = new PriorityQueue<>();
            Set<Integer> visited = new HashSet<>();

            // 1. 모든 파이어볼 이동
            while (!q.isEmpty()) {
                Node cur = q.poll();
                cur.r = (dr[cur.d] * cur.s + cur.r + (N * 1000))%N;
                cur.c = (dc[cur.d] * cur.s + cur.c + (N * 1000))%N;

                if (board[cur.r][cur.c] == null)
                    board[cur.r][cur.c] = new ArrayList<>();

                board[cur.r][cur.c].add(cur);

                int pos = cur.r * N + cur.c;
                if (!visited.contains(pos)) {
                    qq.offer(pos);
                    visited.add(pos);
                }
            }

            // 2. 합치고 나누기
            while (!qq.isEmpty()) {
                int n = qq.poll();
                int r = n / N;
                int c = n % N;

                if (board[r][c].size() == 1) {
                    // 파이어볼이 1개면 그대로 추가
                    q.offer(board[r][c].get(0));
                } else {
                    // 파이어볼이 2개 이상이면 합치고 나누기
                    int m = 0, s = 0;
                    boolean allEven = true, allOdd = true;
                    int ssize = board[r][c].size();

                    for (Node cc : board[r][c]) {
                        m += cc.m;
                        s += cc.s;
                        if (cc.d % 2 == 0) {
                            allOdd = false;
                        } else {
                            allEven = false;
                        }
                    }

                    m /= 5;
                    s /= ssize;

                    if (m > 0) {
                        int start = (allEven || allOdd) ? 0 : 1;
                        for (int i = start; i < 8; i += 2) {
                            q.offer(new Node(r, c, m, s, i));
                        }
                    }
                }
            }
        }

        // 남은 파이어볼의 질량 합 계산
        int ans = 0;
        while (!q.isEmpty())
            ans += q.poll().m;

        return ans;
    }
}