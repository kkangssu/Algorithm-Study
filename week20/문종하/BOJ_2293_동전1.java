package ccc;

import java.io.IOException;

// 1024 * 1024 * 1024 => 1,000,000,000
public class BOJ_2293_동전1 {

	public static void main(String[] args) throws IOException {
		int ans = 0;
		int n = readInt();
		int k = readInt();

		int[] coins = new int[n];
		for (int i = 0; i < n; i++) {
			coins[i] = readInt();
		}

		int[] dp = new int[k + 1];

		dp[0] = 1;

		for (int coin : coins) {
			for (int i = coin; i <= k; i++) {
				dp[i] += dp[i - coin];
			}
		}

		System.out.println(dp[k]);
	}

	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		while (read < '0' || read > '9') {
			read = System.in.read();
		}
		while (read >= '0' && read <= '9') {
			result = 10 * result + read - '0';
			read = System.in.read();
		}
		return result;
	}
}
