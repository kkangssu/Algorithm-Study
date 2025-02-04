import java.io.*;
import java.util.*;

class Main {
    // N: 전깃줄의 개수 + 1 (1-based indexing 사용)
    // ans: 가장 긴 증가하는 부분 수열의 길이
    static int N, ans;
    // arr: 전깃줄의 연결 정보를 저장하는 2차원 배열 [i][0]: A전봇대 위치, [i][1]: B전봇대 위치
    static int[][] arr;
    // dp: 각 위치까지의 최장 증가 부분 수열의 길이를 저장하는 배열
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 입력 처리: 전깃줄 개수 (+1은 1-based indexing을 위함)
        N = Integer.parseInt(br.readLine()) + 1;
        arr = new int[N][2];
        dp = new int[N];

        // 전깃줄 연결 정보 입력
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // A전봇대 위치
            arr[i][1] = Integer.parseInt(st.nextToken()); // B전봇대 위치
        }
        
        // A전봇대 위치를 기준으로 오름차순 정렬
        // 이렇게 하면 B전봇대의 번호로 LIS를 구할 수 있음
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
        ans = 1;

        // LIS(최장 증가 부분 수열) 알고리즘 적용
        for (int i = 1; i < N; i++) {
            dp[i] = 1; // 초기값: 자기 자신만으로 이루어진 수열의 길이 1
            // 현재 위치(i) 이전의 모든 위치(j)에 대해 검사
            for (int j = 1; j < i; j++) {
                // 현재 B전봇대 위치가 이전 B전봇대 위치보다 크고
                // 이전까지의 최장 길이+1이 현재 저장된 길이보다 크면 갱신
                if (arr[i][1] > arr[j][1] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            // 전체 최장 길이 갱신
            if (ans < dp[i])
                ans = dp[i];
        }

        // 정답 출력: 전체 전깃줄 수 - 최장 증가 수열의 길이
        // (제거해야 할 전깃줄의 최소 개수)
        System.out.println(--N - ans);
    }
}