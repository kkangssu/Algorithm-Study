import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2293_동전 {
	static int N, M;
	static int[] coin,dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		coin = new int[N+1];
		dp = new int[M+1];
		dp[0] = 1;
		
		for (int i = 1; i < N+1; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			for(int j = coin[i]; j< M+1; j++) {
				dp[j] += dp[j - coin[i]];
			}
		}
		System.out.println(dp[M]);

	}

}
