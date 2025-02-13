import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static int N, K;
	static int[] map;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N];
		for(int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(map);
		
		dp = new int[K+1];
		dp[0] = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = map[i]; j <= K; j++) {
				
				dp[j] += dp[j-map[i]];
			}
		}
		
		System.out.println(dp[K]);
				
		
	}
	
}
