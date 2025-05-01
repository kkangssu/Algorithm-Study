import java.util.Arrays;

public class PROGRAMMERS_섬연결하기 {
    public static void main(String[] args) {
        // Test case
        int n = 4;
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        Solution2 solution = new Solution2();
        int result = solution.solution(n, costs);
        System.out.println("Minimum cost to connect all islands: " + result);
    }
}

class Solution2 {
    public int solution(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }

        int cnt = 0;

        for (int[] cost : costs) {
            int from = cost[0];
            int to = cost[1];
            int val = cost[2];

            int fx = findSet(p, from);
            int fy = findSet(p, to);


            if (fx != fy) {
                union(p, fx, fy);
                answer += val;
                cnt++;

                if (cnt == n - 1) break;
            }
        }

        return answer;
    }
    public int findSet(int[] p, int x) {
        if(p[x] != x) p[x] = findSet(p, p[x]);
        return p[x];
    }

    public void union(int[] p, int x, int y) {
        p[y] = x;
    }
}