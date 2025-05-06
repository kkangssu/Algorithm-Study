import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D;
    static int[] archers = new int[3];
    static int[][] map;
    static int MAX = 0;

    // 좌표와 거리 정보를 담기 위한 클래스
    static class Point {
        int r, c, distance;

        Point(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 궁수 위치 3명을 조합으로 선택
        selectArcherPositions(0, 0);
        
        System.out.println(MAX);
    }
    
    // 궁수 3명의 위치를 조합으로 선택하는 함수
    public static void selectArcherPositions(int count, int start) {
        // 궁수 3명 선택 완료 시 시뮬레이션 실행
        if (count == 3) {
            MAX = Math.max(MAX, simulateGame());
            return;
        }
        
        // 가능한 열 위치 중 조합 선택
        for (int i = start; i < M; i++) {
            archers[count] = i;
            selectArcherPositions(count + 1, i + 1);
        }
    }
    
    // 게임 시뮬레이션
    public static int simulateGame() {
        int enemiesKilled = 0;
        
        // 원본 맵을 복사하여 시뮬레이션용 맵 생성
        int[][] gamemap = new int[N][];
        for (int i = 0; i < N; i++) {
            gamemap[i] = new int[M];
            System.arraycopy(map[i], 0, gamemap[i], 0, M);
        }
        
        // N턴 동안 진행 (각 턴마다 적들이 한 칸씩 내려옴)
        for (int turn = 0; turn < N; turn++) {
            Set<Point> targetsToRemove = new HashSet<>();
            
            // 각 궁수의 공격 대상 선정
            for (int archerIdx = 0; archerIdx < 3; archerIdx++) {
                int archerPos = archers[archerIdx];
                Point target = findTarget(gamemap, archerPos);
                if (target != null) {
                    targetsToRemove.add(target); // 중복 공격 방지
                }
            }
            
            // 선정된 적들을 제거
            for (Point target : targetsToRemove) {
                int r = target.r;
                int c = target.c;
                if (gamemap[r][c] == 1) {
                    gamemap[r][c] = 0;
                    enemiesKilled++;
                }
            }
            
            // 적들을 아래로 한 칸 이동
            moveEnemies(gamemap);
        }
        
        return enemiesKilled;
    }
    
    private static void moveEnemies(int[][] gamemap) {
        for (int i = N - 1; i > 0; i--) {
            System.arraycopy(gamemap[i-1], 0, gamemap[i], 0, M);
        }
        Arrays.fill(gamemap[0], 0); // 맨 윗줄 초기화
    }

    // 궁수가 공격할 가장 가까운 적을 탐색하는 함수
    private static Point findTarget(int[][] gamemap, int archerCol) {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(N, archerCol, 0)); // 궁수는 N행에 있다고 가정
        
        // BFS 탐색 순서: 왼쪽 → 위쪽 → 오른쪽
        int[] dr = {0, -1, 0};
        int[] dc = {-1, 0, 1};

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int dir = 0; dir < 3; dir++) {
                int nr = now.r + dr[dir];
                int nc = now.c + dc[dir];
                int nd = now.distance + 1;

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
                    visited[nr][nc] = true;

                    if (gamemap[nr][nc] == 1 && nd <= D) {
                        return new Point(nr, nc, nd); // 가장 가까운 적 발견
                    }

                    if (nd < D) {
                        queue.offer(new Point(nr, nc, nd)); // 계속 탐색
                    }
                }
            }
        }

        return null; // 사거리 내 적 없음
    }
}
