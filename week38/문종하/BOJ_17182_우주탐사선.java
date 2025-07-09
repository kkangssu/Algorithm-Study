import java.io.*;
import java.util.*;

/*
    모든 행성 탐사 => 최소 시간 계산
    행성 간 이동 시간 2차원 행렬

    행성의 개수 N
     (2 ≤ N ≤ 10, 0 ≤ K < N)
     (0 ≤ Tij  ≤ 1000)

     2098 외판원 순회
*/
public class BOJ_17182_우주탐사선 {
    static int N, K, NN;
    static int[][] dist;
    static int[][] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        NN = (1 << N) - 1;

        dist = new int[N][N];
        dp = new int[1 << N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                dist[i][j] = Integer.parseInt(st.nextToken());
        }

        floyd();

        for (int i = 0; i < (1 << N); i++)
            Arrays.fill(dp[i], -1);

        System.out.println(tsp(1 << K, K));
    }

    static void floyd() {
        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }

    static int tsp(int V, int cur) {
        if (V == NN)
            return 0;

        if (dp[V][cur] != -1)
            return dp[V][cur];

        dp[V][cur] = INF;

        for (int next = 0; next < N; next++)
            if ((V & (1 << next)) == 0)
                dp[V][cur] = Math.min(dp[V][cur], dist[cur][next] + tsp(V | (1 << next), next));

        return dp[V][cur];
    }
}