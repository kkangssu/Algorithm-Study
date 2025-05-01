class Solution {
    public int solution(int n, int[][] results) {
        int[][] wins = new int [n+1][n+1];
        for(int i = 0; i<results.length; i++){
            int winner = results[i][0];
            int loser = results[i][1];
            // 이긴 사람 1점 진 사람 -1점
            wins[winner][loser] = 1;
            wins[loser][winner] = -1;
        }
        
        // i가 k를 이기고 k가 j를 이기면 i는 j를 이긴다
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(wins[i][k] == 1 && wins[k][j] == 1){
                        wins[i][j] = 1;
                        wins[j][i] = -1;
                    }
                }
            }
        }
        
        int cnt = 0;
        int answer = 0;
        for(int i = 1; i<=n; i++){
            cnt = 0;
            for(int j = 1; j<=n; j++){
                if(i == j) continue;
                if(wins[i][j] != 0){
                    cnt++;
                }
            }
            // wins 배열에 n-1 만큼의 결과가 있다면 이 사람의 순위는 확정된 것
            if(cnt == n-1) answer++;
        }
        
        return answer;
    }
}