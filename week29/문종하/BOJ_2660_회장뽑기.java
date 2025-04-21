import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2660_회장뽑기 {
    static int N,M, ans;
    static int[][] dist;
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dist = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1) break;

            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int[] point = new int[N+1];
        int min = INF;

        for(int i = 1; i <= N; i++) {
            int max = 0;
            for(int j = 1; j <= N; j++) {
                if(i != j) {
                    max = Math.max(max, dist[i][j]);
                }
            }
            point[i] = max;
            min = Math.min(min, max);
        }

        List<Integer> li = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(point[i] == min) {
                li.add(i);
            }
        }

        System.out.println(min + " " + li.size());
        for(int num : li) {
            System.out.print(num + " ");
        }
    }
}
