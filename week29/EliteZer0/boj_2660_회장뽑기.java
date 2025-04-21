import java.util.*;
import java.io.*;

/*
* 회장 후보의 점수 계산
* 모든 사람과 연결되기까지 가장 많이 거쳐야하는 엣지 수z
* 예를 들어 한 명이라도 한 다리를 거쳐야하는 경우 2점 두다리를 거쳐야하는 경우 3점 이런 형식
* 50명 이하 -> 플로이드 워셜 사용 가능
*/
public class Main {
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dp = new int[51][51];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j)
                    dp[i][j] = 0;
                else
                    dp[i][j] = 50; // 최대로 많이 건널 수 있는 다리는 49개
            }
        }

        while (true) {
            String input = br.readLine();
            if (input.equals("-1 -1"))
                break;
            st = new StringTokenizer(input);
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            dp[p1][p2] = 1;
            dp[p2][p1] = 1;
        }

        int min = 50; // 최대로 많이 건널 수 있는 다리는 49개

        // 플로이드워샬
        for (int i = 1; i <= N; i++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    if (a == b || a == i || b == i)
                        continue;
                    // dp[a][b] 가 dp[a][i] 와 dp[i][b] 보다 크다면 업데이트
                    if (dp[a][b] > dp[a][i] + dp[i][b]) {
                        dp[a][b] = dp[a][i] + dp[i][b];
                    }
                }
            }
        }

        // 모든 사람에 대해, 이 사람이 가장 많이 거쳐야하는 다리를 찾기. 그 값이 최소인 사람들을 후보군에 추가
        ArrayList<Integer> candidate = new ArrayList<>(50);
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = 0;
            for (int j = 1; j <= N; j++) {
                max = Math.max(max, dp[i][j]);
            }
            if (min == max)
                candidate.add(i);
            else if (min > max) {
                candidate.clear();
                min = max;
                candidate.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(candidate.size()).append("\n");

        for (Integer i : candidate) {
            sb.append((i)).append(" ");
        }

        System.out.println(sb.toString());
    }
}