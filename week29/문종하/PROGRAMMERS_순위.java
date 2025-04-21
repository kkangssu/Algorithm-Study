import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PROGRAMMERS_순위 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트용 입력
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};

        Solution solution = new Solution();
        System.out.println(solution.solution(n, results));
    }
}

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;

        boolean[][] dist = new boolean[n+1][n+1];

        for(int[] res : results) {
            int w = res[0];
            int l = res[1];
            dist[w][l] = true;
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(dist[i][k] && dist[k][j]) {
                        dist[i][j] = true;
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            int count = 0;

            for(int j = 1; j <= n; j++) {
                if(i == j) continue;

                if(dist[i][j] || dist[j][i])
                    count++;
            }

            if(count == n-1)  answer++;
        }

        return answer;
    }
}