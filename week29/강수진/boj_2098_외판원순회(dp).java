import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int[][] dp, cost;
    static int n;
    static int INF = 987654321;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j < n; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][1<<n];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], -1);
        }

      // 순회할 것이기 때문에 어디서 시작해도 상관 없음
      // 편의상 0번에서 시작, 0번 visited함 = 1
       int result = travel(0, 1);
        System.out.println(result);
    }

  // idx: 현재 위치한 도시, visited: 지금까지 방문한 도시들의 상태를 비트마스크로 표현
    static int travel(int idx, int visited){
      // 모든 도시 방문
        if(visited == (1<<n)-1){
          // 처음 위치로 갈 수 있다면 cost[idx][0] 반환
            return cost[idx][0] != 0? cost[idx][0] : INF;
        }

      // 이미 계산된 상태라면 저장된 값 반환: 메모이제이션
        if(dp[idx][visited] != -1) return dp[idx][visited];

        dp[idx][visited] = INF;

      // 다음에 방문할 도시를 선택
        for(int i = 0; i < n; i++){
            if((visited & (1 << i)) != 0) continue;  // 이미 방문한 도시 continue
            if(cost[idx][i] == 0) continue;  // 현재 도시에서 갈 수 없는 경우 continue

            int next = visited | (1 << i);  // visited 갱신
            int nextCost = cost[idx][i] + travel(i, next);  // 다음 도시로 이동한 비용 + 그 이후의 모든 도시를 방문하는 최소 비용

            dp[idx][visited] = Math.min(dp[idx][visited], nextCost);  // 현재까지의 최소 비용과 새로 계산한 비용 중 작은 값을 선택
        }

        return dp[idx][visited];
    }
}
