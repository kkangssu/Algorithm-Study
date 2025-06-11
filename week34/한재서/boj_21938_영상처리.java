import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 사진의 크기 (행 N, 열 M)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] picture = new int[N][M];  // 영상 데이터 저장할 배열
        int[] dy = {1, 0, -1, 0};  // 4방향 (상, 우, 하, 좌)
        int[] dx = {0, 1, 0, -1};

        // 영상 데이터 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                // 각 픽셀의 RGB 값을 받아서 평균을 구함 → 흑백 변환
                picture[i][j] = (
                    Integer.parseInt(st.nextToken()) +  // R
                    Integer.parseInt(st.nextToken()) +  // G
                    Integer.parseInt(st.nextToken())    // B
                ) / 3;  // 단순 평균으로 밝기 계산
            }
        }

        // 밝기 임계값 (threshold) 입력
        int T = Integer.parseInt(br.readLine());

        int cnt = 0;  // 밝기가 T 이상인 영역의 개수 (결과값)
        boolean[][] visited = new boolean[N][M];  // 방문 체크 배열

        // 전체 좌표 순회하며 BFS 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                // 조건: 방문하지 않았고, 밝기가 임계값 이상일 때만 탐색 시작
                if (picture[i][j] < T || visited[i][j])
                    continue;

                // 새로운 영역 발견 → 카운트 증가
                cnt++;

                // BFS 탐색 시작
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] {i, j});
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        int ny = cur[0] + dy[k];
                        int nx = cur[1] + dx[k];

                        // 유효 좌표 체크 + 임계값 체크 + 미방문 체크
                        if (ny < 0 || ny >= N || nx < 0 || nx >= M || picture[ny][nx] < T || visited[ny][nx])
                            continue;

                        // 방문 처리 및 큐에 추가
                        queue.offer(new int[] {ny, nx});
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(cnt);
    }
}
