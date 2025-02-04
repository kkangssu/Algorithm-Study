package baekjoon_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 전깃줄 {

	static int N;
	static int[][] map;
	static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][2];
		
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(map, (o1,o2)->{
			return o1[0] - o2[0];
		});
	
		dp = new int[N];
		Arrays.fill(dp, 1);
		
		int answer = 0;
		
		for(int i = 0; i < N; i++) {
			
			for(int j = 0; j < i; j++) {
				if(map[i][1] > map[j][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			answer = Math.max(dp[i], answer);
		}
	
		System.out.println(N-answer);
		
	}

}
