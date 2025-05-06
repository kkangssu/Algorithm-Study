import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
     궁수 3명
     하나의 칸에는 최대 1명의 궁수
     모든 궁수는 동시에 공격
     D이하인 적 중에서 가장 가까운 적
     그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격
     같은 적이 여러 궁수에게 공격 가능
     성벽 궁수가 한칸씩 올라감 (적 위치는 고정)
*/
public class BOJ_17135_캐슬디펜스 {
    static int N, M, D, ans;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(new int[3], 0, 0);

        System.out.println(ans);
    }

    private static void comb(int[] archers, int cnt, int s) {
        if (cnt == 3) {
            solve(archers);
            return;
        }

        for (int i = s; i < M; i++) {
            archers[cnt] = i;
            comb(archers, cnt + 1, i + 1);
        }
    }

    private static void solve(int[] archers) {
        int[][] map = copyMap(board);

        // 느림
//        int[][] map = Arrays.stream(board)
//                .map(int[]::clone)
//                .toArray(int[][]::new);
        
        int cnt = 0;

        // 성벽 위치
        int cur = N;

        while (cur > 0) {
            // 1. 적 조준
            List<int[]> targets = new ArrayList<>();

            for (int x : archers) {
                int[] target = findTarget(map, cur, x);
                if (target != null) {
                    targets.add(target);
                }
            }

            // 2. 적 제거, 동일 타겟 거르기 위해
            for (int[] target : targets) {
                int r = target[0];
                int c = target[1];
                if (map[r][c] == 1) {
                    map[r][c] = 0;
                    cnt++;
                }
            }

            // 3. 성벽 한 칸 위 이동 => 궁수 한 칸 위로
            cur--;
        }

        ans = Math.max(ans, cnt);
    }

    // 가장 가까운 적 찾기
    private static int[] findTarget(int[][] map, int cur, int x) {
        int min = Integer.MAX_VALUE;
        int minR = -1;
        int minC = -1;

        for (int r = 0; r < cur; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {

                    int dist = Math.abs(cur - r) + Math.abs(x - c);

                    // 가까운 적
                    if (dist <= D) {
                        if (dist < min || (dist == min && c < minC)) {
                            min = dist;
                            minR = r;
                            minC = c;
                        }
                    }
                }
            }
        }

        if (minR != -1) {
            return new int[] {minR, minC};
        }
        return null;
    }
    
    private static int[][] copyMap(int[][] original) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }
}