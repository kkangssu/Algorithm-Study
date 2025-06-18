package week35.EliteZer0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11066_파일합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K + 1];
            int[] prefix = new int[K + 1]; // 누적합 배열

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                prefix[i] = prefix[i - 1] + files[i]; // prefix[i]: 1~i까지의 총 파일 크기
            }

            int[][] dp = new int[K + 1][K + 1]; // dp[i][j]: i~j 구간을 합치는 최소 비용

            // 구간의 길이(len)를 2부터 시작해서 K까지 차례대로 처리
            for (int len = 2; len <= K; len++) {
                for (int i = 1; i <= K - len + 1; i++) {
                    int j = i + len - 1; // 구간의 끝 인덱스
                    dp[i][j] = Integer.MAX_VALUE; // 초기값을 무한대로 설정

                    // i~j 구간을 k를 기준으로 두 부분으로 나누어서 최소 비용 계산
                    for (int k = i; k < j; k++) {
                        // dp[i][k]: 왼쪽 부분을 합치는 최소 비용
                        // dp[k+1][j]: 오른쪽 부분을 합치는 최소 비용
                        // prefix[j] - prefix[i - 1]: i~j 구간 전체 크기
                        int cost = dp[i][k] + dp[k + 1][j] + prefix[j] - prefix[i - 1];

                        // 가장 작은 비용으로 갱신
                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                }
            }

            System.out.println(dp[1][K]);
        }
    }
}
