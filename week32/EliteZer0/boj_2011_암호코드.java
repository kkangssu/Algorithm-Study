package week32;

import java.io.*;

public class boj_2011_암호코드 {
    static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String code = br.readLine();
        int n = code.length();

        // dp[i] = i번째 문자까지 해석하는 경우의 수
        int[] dp = new int[n + 1];
        
        // 아무 것도 해석하지 않은 경우는 1가지 (기본값)
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            // 현재 위치의 한 자리 숫자
            int oneDigit = code.charAt(i - 1) - '0';

            // 한 자리 숫자가 1~9이면 해석 가능 (0은 해석 불가)
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
                dp[i] %= MOD;
            }

            // 두 자리 수 판단은 i가 2 이상일 때만 가능
            if (i == 1) continue;

            // 앞자리 숫자 (i-2 위치)
            int prevDigit = code.charAt(i - 2) - '0';

            // 앞자리가 0이면 두 자리 수 불가 (예: "01"은 의미 없음)
            if (prevDigit == 0) continue;

            // 두 자리 숫자 생성
            int twoDigit = prevDigit * 10 + oneDigit;

            // 두 자리 숫자가 10~26이면 알파벳으로 변환 가능
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= MOD;
            }
        }

        System.out.println(dp[n]);
    }
}
