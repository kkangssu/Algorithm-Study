class Solution {
    public int solution(int n, int[][] results) {
        final int INF = 100000000;
        int[][] relations = new int[n][n];
        int ans = 0;
        
        for(int i=0; i < n; i++) {
            for(int j=0; j < n; j++) {
                if(i == j) continue;
                relations[i][j] = INF;
            }
        }
        
        for(int i=0; i < results.length; i++)
            relations[results[i][0] - 1][results[i][1] - 1] = 1;

        for(int k=0; k < n; k++) {
            for(int i=0; i < n; i++) {
                for(int j=0; j < n; j++)
                    relations[i][j] = Math.min(relations[i][j], relations[i][k] + relations[k][j]);
            }
        }
        
        for(int i=0; i < n; i++) {
            int cnt = 0;
            for(int j=0; j < n; j++) {
                if(relations[i][j] != INF || relations[j][i] != INF)
                    cnt++;
            }
            
            if(cnt == n)
                ans++;
        }
        
        return ans;
    }
}
