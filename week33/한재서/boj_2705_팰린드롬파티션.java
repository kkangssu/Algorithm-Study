import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수 입력

        int[] dp = new int[1001];  // DP 배열 선언 (최대 1000까지 미리 계산)

        // 초기값 설정
        for(int i=3; i < 1001; i++)
            dp[i] = -1;  // (사실상 필요 없는 초기화지만 혹시 모를 초기 상태 대비)

        dp[1] = 1;  // 기본 케이스 1 설정
        dp[2] = 2;  // 기본 케이스 2 설정

        // DP 배열 사전 계산 (미리 전부 계산해두고, 질의 때 빠르게 출력)
        for(int i=3; i < 1001; i++) {
            if(i % 2 == 0)  // i가 짝수일 때
                dp[i] = dp[i - 1] + dp[i / 2];
            else  // 홀수일 때
                dp[i] = dp[i - 1];
        }
        
        // 테스트 케이스 입력 및 정답 출력
        for(int t=0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb.toString());  // 최종 출력
    }
}
