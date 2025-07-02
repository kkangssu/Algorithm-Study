import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
    static int N, M, ans;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static List<int[]> emptyCells;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;
        map = new int[N][M];
        emptyCells = new ArrayList<>();

        for(int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    emptyCells.add(new int[] {i, j});
            }
        }

        for(int i=0; i < emptyCells.size(); i++) {
            for(int j=i + 1; j < emptyCells.size(); j++)
                baduk(emptyCells.get(i), emptyCells.get(j));
        }

        System.out.println(ans);
    }

    static void baduk(int[] arr1, int[] arr2) {
        map[arr1[0]][arr1[1]] = 1;
        map[arr2[0]][arr2[1]] = 1;
        visited = new boolean[N][M];
        int temp = 0;

        for(int i=0; i < N; i++) {
            for(int j=0; j < M; j++) {
                if(visited[i][j] || map[i][j] < 2)
                    continue;

                temp += checkSize(i, j);
                
            }
        }

        ans = Math.max(ans, temp);

        map[arr1[0]][arr1[1]] = 0;
        map[arr2[0]][arr2[1]] = 0;
    }

    static int checkSize(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {y, x});
        visited[y][x] = true;
        int size = 0;
        boolean isCaptured = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            size++;

            for(int i=0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if(!check(ny, nx))
                    continue;

                if(map[ny][nx] == 0)
                    isCaptured = false;
                else if(map[ny][nx] == 2 && !visited[ny][nx]) {
                    queue.offer(new int[] {ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }

        return isCaptured ? size : 0;
    }

    static boolean check(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}
