import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011_암호코드 {
    static final int MOD = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        char [] c = str.toCharArray();
        int len = c.length;

        if (len == 0 || c[0] == '0') {
            System.out.println(0);
            return;
        }

        int[] dp = new int[len + 1];

        dp[0] = 1; dp[1] = 1;

        for (int i = 2; i <= len; i++) {
            if (c[i - 1] != '0')
                dp[i] = dp[i - 1];

            int num = Integer.parseInt(str.substring(i - 2, i));
            if (num >= 10 && num <= 26) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }

            if (dp[i] == 0)
                if (i < len) {
                    System.out.println(0);
                    return;
                }
        }

        System.out.println(dp[len]);
    }

}