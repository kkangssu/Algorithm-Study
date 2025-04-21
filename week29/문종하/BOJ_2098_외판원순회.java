import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098_외판원순회 {
    static int N,ans;
    static int[][] W;
    static int[][] dp;
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                W[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[(1<<N)][N];
        for (int i = 0; i < (1<<N); i++) {
            Arrays.fill(dp[i], -1);
        }

        ans = tsp(1,0);

        System.out.println(ans);
    }
    static int tsp(int bit, int cur){
        if(bit == (1<<N)-1){
            return W[cur][0]!=0?W[cur][0]:INF;
        }

        if(dp[bit][cur]!=-1) return dp[bit][cur];

        dp[bit][cur] = INF;

        for(int i = 0; i < N; i++) {
            if((bit & (1<<i)) == 0 && W[cur][i] != 0) {
                int n = W[cur][i]+tsp((bit | (1<<i)),i);
                dp[bit][cur] = Math.min(dp[bit][cur], n);
            }
        }

        return dp[bit][cur];
    }
}
