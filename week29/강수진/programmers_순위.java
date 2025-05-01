class Solution {
    public int solution(int n, int[][] results) {
        // dp[i][j]: i번 선수가 j번 선수와의 관계
        // 1: i가 j를 이김, -1: i가 j에게 짐, 0: 알 수 없음
        int[][] dp = new int[n+1][n+1];

        for(int i = 0; i < results.length; i++){
            int a = results[i][0];
            int b = results[i][1];
            // a가 b를 이김
            dp[a][b] = 1;
            dp[b][a] = -1;
        }

      // 선수 l, r 비교 -> 선수 m을 거쳐서
        for(int m = 1; m < n+1; m++){
            for(int l = 1; l < n+1; l++){
                for(int r = 1; r < n+1; r++){
                    // l이 m을 이김 + m이 r을 이김 -> l은 r을 이김
                    if(dp[l][m] == 1 && dp[m][r] == 1){
                        dp[l][r] = 1;
                        dp[r][l] = -1;
                    }
                    // l이 m에게 짐 + m이 r에게 짐 -> l은 r에게 짐
                    if(dp[l][m] == -1 && dp[m][r] == -1){
                        dp[l][r] = -1;
                        dp[r][l] = 1;
                    } 
                }
            }
        }

        int result = 0;  // 순위 알 수 있는 선수
        for(int i = 1; i < n+1; i++){  // 선수 i의 상태 확인
            int cnt = 0;
            for(int j = 1; j < n+1; j++){
                if(dp[i][j] != 0) cnt++;  // 선수 i와 선수 j 사이의 결과가 정해져있으면 cnt++
            }
            if(cnt == n-1) result++;  // 선수 i의 결과가 모두 정해져 있음 = 순위를 알 수 있는 선수
        }

        return result;
    }
}
