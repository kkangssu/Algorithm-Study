import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, Q, land, total;
    static int[][] field;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        N = 1 << N;
        Q = Integer.parseInt(st.nextToken());
        field = new int[N][N];

        for(int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < N; j++)
                field[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] sequence = new int[Q];
        for(int i=0; i < Q; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i < Q; i++) {
            field = divide(sequence[i]);
            field = melt();
        }

        land = 0;
        total = 0;

        findBiggest();
        sb.append(total).append("\n").append(land);
        System.out.println(sb.toString());
    }

    static int[][] divide(int L) {
        int[][] temp = new int[N][N];
        L = 1 << L;
        for(int i=0; i < N; i += L) {
            for(int j=0; j < N; j += L)
                rotate(i, j, L, temp);
        }

        return temp;
    }

    static void rotate(int y, int x, int L, int[][] temp) {
        for(int i=0; i < L; i++) {
            for(int j=0; j < L; j++) {
                temp[y + i][x + j] = field[y + L - 1 - j][x + i];
            }
        }
    }

    static int[][] melt() {
        int[][] temp = new int[N][N];
        for(int i=0; i < N; i++)
            temp[i] = Arrays.copyOf(field[i], N);

        for(int i=0; i < N; i++) {
            for(int j=0; j < N; j++) {
                int cnt = 0;
                if(field[i][j] == 0)
                    continue;

                for(int k=0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if(check(ny, nx) && field[ny][nx] > 0)
                        cnt++;
                }

                if(cnt < 3)
                    temp[i][j]--;
            }
        }

        return temp;
    }

    static void findBiggest() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for(int i=0; i < N; i++) {
            for(int j=0; j < N; j++) {
                total += field[i][j];
                if(field[i][j] > 0 && !visited[i][j]) {
                    queue.add(new int[] {i, j});
                    visited[i][j] = true;
                    int cnt = 1;

                    while(!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for(int k=0; k < 4; k++) {
                            int ny = cur[0] + dy[k];
                            int nx = cur[1] + dx[k];
                            if(check(ny, nx) && field[ny][nx] > 0 && !visited[ny][nx]) {
                                visited[ny][nx] = true;
                                queue.add(new int[] {ny, nx});
                                cnt++;
                            }
                        }
                    }

                    land = Math.max(land, cnt);
                }
            }
        }
    }

    static boolean check(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}
