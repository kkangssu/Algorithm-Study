import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

    1
    2   11
    3   111
    4   1111        121     22
    5   11111       131
    6   111111      11211   222     141     33
    7   1111111     11311   232     151     313
    8   11111111    44      121121  2222    112211  211112    323       3113        1112111
                            242     11411   21212           161     12221   1331


    15  1+2+1+7+1+2+1

    1. 2등분, 3등분으로 나눈다
    2. 가운데 높은 수를 가장자리로 옮겨가면서 경우의수 구한다.
    3. 기존 계산했던 경우의 수를 계산한다.

    홀수
    짝수
*/
public class BOJ_2705_팰린드롬파티션 {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        dp = new int[1001];
        make();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }

        System.out.print(sb);
    }

    static void make() {
        dp[0] = 1;

        for (int n = 1; n <= 1000; n++) {
            dp[n] = 1;

            // 길이가 홀수인 팰린드롬
            // 가운데 원소 + 양쪽 대칭
            for (int mid = 1; mid <= n; mid++) {
                int remaining = n - mid;
                if (remaining > 0 && remaining % 2 == 0) {
                    int halfSum = remaining / 2;
                    dp[n] += dp[halfSum];
                }
            }

            // 길이가 짝수인 팰린드롬
            // 양쪽 완전 대칭
            if (n % 2 == 0) {
                int halfSum = n / 2;
                dp[n] += dp[halfSum];
            }
        }
    }
}
