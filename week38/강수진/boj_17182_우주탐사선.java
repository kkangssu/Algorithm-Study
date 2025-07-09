import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17182_우주탐사선 {

    static final int INF = 987654321;
    static int N;
    static int[][] dp, time;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        time = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Floyd-Warshall -> 최단 시간 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    time[j][k] = Math.min(time[j][k], time[j][i] + time[i][k]);
                }
            }
        }

        // 초기화
        dp = new int[1 << N][N];
        for (int i = 0; i < (1 << N); i++) {
            Arrays.fill(dp[i], INF);
        }

        // K에서 시작
        dp[1 << K][K] = 0;

        for(int visit = 0; visit < (1 << N); visit++) {
            for(int now = 0; now < N; now++) {
                if(dp[visit][now] == INF) continue;
                if((visit & (1 << now)) == 0) continue;

                for(int next = 0; next < N; next++) {
                    int nextVisit = visit | (1 << next);
                    dp[nextVisit][next] = Math.min(dp[nextVisit][next], dp[visit][now] + time[now][next]);
                }
            }
        }

        int min = INF;
        for(int i = 0;  i <  N; i++){
            min = Math.min(min, dp[(1 << N) - 1][i]);
        }

        System.out.println(min);
    }
}
