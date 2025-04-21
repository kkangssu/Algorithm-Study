import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1389_케빈베이컨의6단계법칙 {
    static int N,M, ans;
    static int[][] dist;
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1][N+1];

        for(int i=1;i<=N;i++) {
            Arrays.fill(dist[i],INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int stt = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            dist[stt][end] = 1;
            dist[end][stt] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int cur = INF;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++){
                sum += dist[i][j];
            }
            System.out.println(sum);
            if (sum < cur) {
                cur = sum;
                ans = i;
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(dist[i]));
        }

        System.out.println(ans);
    }
}