import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] picture = new int[N][M];
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        for(int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < M; j++) {
                picture[i][j] = (
                    Integer.parseInt(st.nextToken()) +
                    Integer.parseInt(st.nextToken()) +
                    Integer.parseInt(st.nextToken())
                ) / 3;
            }
        }

        int T = Integer.parseInt(br.readLine());
        int cnt = 0;
        boolean[][] visited = new boolean[N][M];
        for(int i=0; i < N; i++) {
            for(int j=0; j < M; j++) {
                if(picture[i][j] < T || visited[i][j])
                    continue;

                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] {i, j});
                visited[i][j] = true;
                cnt++;
                while(!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for(int k=0; k < 4; k++) {
                        int ny = cur[0] + dy[k];
                        int nx = cur[1] + dx[k];
                        if(ny < 0 || ny >= N || nx < 0 || nx >= M || picture[ny][nx] < T || visited[ny][nx])
                            continue;
                        queue.offer(new int[] {ny, nx});
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}
