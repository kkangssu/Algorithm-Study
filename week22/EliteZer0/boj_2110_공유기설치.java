import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2110_공유기설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int low = 1;
//        int high = houses[N-1] - houses[0] + 1; -> 실제로는 불가능한 값까지 포함하게 됨
        int high = houses[N-1] - houses[0];
        int result = 0;

//        while (low < high) { -> 최종 답을 찾기 전에 종료될 수 있음
        while(low <= high) {
            int mid = (low + high) / 2;
            // 공유기 설치 첫번째 집부터
            int position = 0;
            int cnt = 1;

            // 설치된 지점에서 현재 집까지의 거리가 미드값보다 크거나 같으면 현재 집에 공유기 설치
            for(int i = 1; i < N; i++) {
                if (houses[i] - houses[position] >= mid) {
                    position = i;
                    cnt++;
                }
            }

            // 설치된 공유기 수가 설치해야할 공유기 수보다 적으면 최소거리 감소
            if(cnt < C){
                high = mid - 1;
                continue;
            } else{
                // 설치된 공유기 수가 설치해야할 공유기 수보다 많다면 최소거리 증가
                result = mid;
                low = mid + 1;
            }
        }

        System.out.println(result);
    }
}

/**
 * 최적해를 구할 때 첫 번째 집부터 설치해도 되는가.
 * - 첫 번째 집에서 시작해도 항상 최적해를 포함하는 해를 찾을 수 있다.
 *      - 최적해의 최소거리를 D라고 하자.
 *      - 첫 번째 집을 제외하고 나머지에 공유기를 설치했을 때, 설치된 거리는 모두 D 이상이다.
 *      - 첫 번째 집을 포함하지 않는 최적해가 있다면
 *      - 첫 번째 집에서 첫 설치 지점까지의 거리도 D 이상이어야한다.
 *      - 첫 번째 집을 포함하지 않는 최적해가 있다면 첫번째집을 포함하는 최적해는 반드시 존재해야한다.
 *
 * - 공유기를 최대한 멀리 설치할 때 양 끝점에 설치하는 게 가장 큰 거리를 확보할 수 있다.
 */