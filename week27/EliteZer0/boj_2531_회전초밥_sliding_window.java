import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_2531_회전초밥_sliding_window {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushis = new int[N];

        for (int i = 0; i < N; i++) {
            sushis[i] = Integer.parseInt(br.readLine());
        }

        // 각 초밥 종류별 개수를 세기 위한 배열 (인덱스는 초밥 종류, 값은 개수)
        // 접시 개수 d + 쿠폰 1개
        int[] sushiCnt = new int[d+1];

        // 현재 연속된 접시에서 먹을 수 있는 초밥 종류의 개수
        int curTypes = 0;

        // 시작 값 설정
        for (int i = 0; i < k; i++) {
            // 이전에 없던 종류의 초밥이면 종류 카운트 증가
            if (sushiCnt[sushis[i]] == 0) {
                curTypes++;
            }
            // 해당 종류의 초밥 개수 증가
            sushiCnt[sushis[i]]++;
        }

        // 현재까지의 최대 초밥 종류 개수 (쿠폰 고려)
        int maxTypes = curTypes;
        // 쿠폰으로 받는 초밥이 현재 윈도우에 없으면 종류 +1
        if (sushiCnt[c] == 0) {
            maxTypes = curTypes + 1;
        }

        // 슬라이딩 윈도우 기법으로 모든 연속된 k개 접시를 확인
        // i는 윈도우의 시작 위치
        for (int i = 1; i < N; i++) {
            // 윈도우 왼쪽에서 빠지는 초밥 처리
            sushiCnt[sushis[i-1]]--;

            // 해당 종류의 초밥이 0개가 되면 종류 카운트 감소
            if (sushiCnt[sushis[i-1]] == 0) {
                curTypes--;
            }

            // 윈도우 오른쪽에 새로 들어오는 초밥 처리
            // 회전 벨트이므로 마지막 접시 다음은 첫 번째 접시
            int newSushi = sushis[(i+k-1) % N];

            // 새로운 종류의 초밥이면 종류 카운트 증가
            if (sushiCnt[newSushi] == 0) {
                curTypes++;
            }

            // 해당 종류의 초밥 개수 증가
            sushiCnt[newSushi]++;

            // 현재 윈도우에서 먹을 수 있는 초밥 종류 개수 (쿠폰 고려 전)
            int totalTypes = curTypes;

            // 쿠폰 초밥이 현재 윈도우에 없으면 종류 +1
            if (sushiCnt[c] == 0) {
                totalTypes++;
            }

            maxTypes = Math.max(maxTypes, totalTypes);
        }

        System.out.println(maxTypes);
    }
}
