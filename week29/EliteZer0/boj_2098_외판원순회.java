import java.io.*;
import java.util.*;

public class Main {
    // n: 도시의 수
    // bit: 모든 도시를 방문했을 때의 비트마스크 값 (2^n - 1)
    static int n, bit;
    static int[][] route;
    // dp[i][j]: i 도시에서 시작해서 j(비트마스크 상태)에 방문했을 때의 최소 비용
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        // 모든 도시를 방문했을 때의 비트마스크 값 계산 (2^n - 1)
        bit = (1<<n) -1;

        route = new int[n][n];
        dp = new int[n][bit];

        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                route[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 순환 경로의 특성 상 이론적으로 어느 도시에서 시작하든 최적 경로의 총 비용은 동일 그러므로 시작도시 0으로 고정
        // 만약 k번 도시에서 시작하고 싶다면, 코드를 TSP(k, 1<<k)로 수정
        System.out.println(TSP(0,1));
    }

    /**
     * TSP (Traveling Salesman Problem) 알고리즘
     * start 현재 위치한 도시
     * check 현재까지 방문한 도시들의 비트마스크
     * 남은 도시들을 모두 방문하고 시작 도시로 돌아가는 최소 비용
     */
    static int TSP(int start, int check) {

        // 모든 도시를 방문했을 경우
        if(check == bit) {
            // 현재 도시에서 시작 도시(0번)로 돌아갈 수 있는지 확인
            // return route[start][0]; // 항상 순회한다고 해서 이렇게 작성했는데 이 경우 불가능한 루트를 사용함
            // if(route[start][0] == 0) return Integer.MAX_VALUE; // 이 경우 쓰레기값 때문에 이상한 값 나옴
            if(route[start][0] == 0) return 987654321; // 경로가 없으면 불가능
            else return route[start][0]; // 경로가 있으면 그 비용 반환
        }

        // 이미 계산된 상태면 저장된 값 반환
        if(dp[start][check] != -1) return dp[start][check];

        // 현재 상태에서의 최소 비용을 큰 값으로 초기화
        // dp[start][check] = Integer.MAX_VALUE; // 이 경우도 쓰레기값으로 오답 나옴
        dp[start][check] = 987654321;

        // 다음에 방문할 도시 선택
        for(int i=0; i<n; i++) {
            // next: 기존 check 비트마스크에 i번째 비트를 1로 설정
            int next = check | (1<<i);

            // 경로가 없거나 이미 방문한 도시는 건너뜀
            /* check & (1<<i) check : 비트마스크에서 i번째 비트만 추출
             * 만약 i번째 비트가 1이면(이미 방문했으면) 결과는 0이 아닌 값
             * 만약 i번째 비트가 0이면(아직 방문하지 않았으면) 결과는 0
             */
            if(route[start][i] == 0 || (check & (1<<i)) != 0) continue;

            // 현재까지의 최소 비용과 i번 도시를 경유하는 비용 중 더 작은 값 선택
            dp[start][check] = Math.min(dp[start][check], TSP(i, next) + route[start][i]);
        }

        // 계산된 최소 비용 반환
        return dp[start][check];
    }
}