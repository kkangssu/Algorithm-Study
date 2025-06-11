import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    // 보드 크기, 파이어볼 개수, 턴 수
    static int n, m, k;

    // 각 칸에 존재하는 파이어볼 리스트 (2차원 배열)
    static List<FireBall>[][] board;

    // 전체 파이어볼 리스트 (시뮬레이션 편의를 위해 관리)
    static List<FireBall> balls;

    // 방향 벡터 (상, 우상, 우, 우하, 하, 좌하, 좌, 좌상)
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1 ,1 ,1, 0, -1, -1, -1};

    // 파이어볼 클래스 (행, 열, 질량, 속력, 방향)
    public static class FireBall {
        int r, c, m, s, d;
        FireBall(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n: 격자 크기, m: 파이어볼 수, k: 명령 횟수
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 보드 초기화 (1-indexed 사용)
        board = new ArrayList[n + 1][n + 1];
        balls = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        // 초기 파이어볼 입력 받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); 
            int c = Integer.parseInt(st.nextToken()); 
            int m = Integer.parseInt(st.nextToken()); 
            int s = Integer.parseInt(st.nextToken()); 
            int d = Integer.parseInt(st.nextToken()); 
            board[r][c].add(new FireBall(r, c, m, s, d));
            balls.add(new FireBall(r, c, m, s, d));
        }

        // k번 명령 수행 (시뮬레이션)
        while (k-- > 0) {
            moveFireBall();  // 1단계: 이동

            // 2단계: 같은 칸에 있는 파이어볼 처리 (합치고 나누기)
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (board[i][j].size() >= 2) {
                        combineAndDivide(i, j, board[i][j]);
                    }
                }
            }

            cleanList();  // 3단계: 리스트 정리 (다음 턴을 위해 balls 리스트 갱신)
        }

        // 남아있는 파이어볼들의 질량 합 구하기
        int answer = 0;
        for (FireBall ball : balls) {
            answer += ball.m;
        }

        System.out.println(answer);
    }

    // 현재 보드 상태를 balls 리스트로 업데이트 (다음 이동 준비)
    public static void cleanList() {
        balls = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (board[i][j].size() > 0) {
                    for (FireBall b : board[i][j]) {
                        balls.add(b);
                    }
                }
            }
        }
    }

    // 모든 파이어볼 이동시키기
    public static void moveFireBall() {
        // 이동 전에 보드 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        // 파이어볼 개별 이동
        for (FireBall ball : balls) {
            int nr = ball.r + dr[ball.d] * (ball.s % n);
            int nc = ball.c + dc[ball.d] * (ball.s % n);

            // 경계를 넘어가면 wrap-around 처리 (1 ~ n)
            if (nr <= 0) nr += n;
            if (nc <= 0) nc += n;
            if (nr > n) nr -= n;
            if (nc > n) nc -= n;

            ball.r = nr;
            ball.c = nc;
            board[nr][nc].add(ball);
        }
    }

    // 같은 칸의 파이어볼을 합치고 나누기
    public static void combineAndDivide(int r, int c, List<FireBall> balls) {
        int m_sum = 0, s_sum = 0;
        boolean isEven = true;
        boolean isOdd = true;

        // 합치면서 질량합, 속력합, 방향 짝/홀 여부 확인
        for (FireBall ball : balls) {
            m_sum += ball.m;
            s_sum += ball.s;
            if (ball.d % 2 == 0) {
                isOdd = false;
            } else {
                isEven = false;
            }
        }

        // 새로 나눠질 파이어볼 질량, 속력 계산
        int nm = m_sum / 5;
        int ns = s_sum / balls.size();

        // 질량이 0이 되면 소멸
        if (nm <= 0) {
            board[r][c] = new ArrayList<>();
            return;
        }

        // 새 방향 결정
        int[] dirs = {0, 2, 4, 6};
        if (!isOdd && !isEven) {  // 방향이 섞여 있으면
            dirs[0] = 1; dirs[1] = 3; dirs[2] = 5; dirs[3] = 7;
        }

        board[r][c] = new ArrayList<>();
        for (int dir : dirs) {
            board[r][c].add(new FireBall(r, c, nm, ns, dir));
        }
    }
}
