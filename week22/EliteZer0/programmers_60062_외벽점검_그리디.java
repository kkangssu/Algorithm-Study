import java.util.*;

// 합계 56.0/100.0
// 두 점 사이의 거리가 가장 먼 경우를 배제하고 탐색하는 방식
public class programmers_60062_외벽점검_그리디 {
    class Solution {
        // 취약점 간의 간격을 표현하는 클래스
        class Gap {
            int index;
            int distance;

            Gap(int index, int distance) {
                this.index = index;
                this.distance = distance;
            }
        }

        public int solution(int n, int[] weak, int[] dist) {
            if (weak.length == 1) return 1;  // 취약점이 1개면 친구 1명만 필요

            // 취약점 간의 간격 계산
            List<Gap> gaps = new ArrayList<>();
            for (int i = 0; i < weak.length - 1; i++) {
                gaps.add(new Gap(i, weak[i + 1] - weak[i]));
            }
            // 마지막 취약점과 첫 취약점 사이의 간격 (원형이므로)
            gaps.add(new Gap(weak.length - 1, n - weak[weak.length - 1] + weak[0]));

            // 간격이 큰 순서대로 정렬
            Collections.sort(gaps, (a, b) -> b.distance - a.distance);

            // 이동 거리가 큰 친구부터 사용
            Arrays.sort(dist);

            // 가장 큰 간격을 제외
            int cutPosition = gaps.get(0).index;
            List<Integer> linearWeak = new ArrayList<>();

            // 선형 취약점 배열 생성 (가장 큰 간격에서 시작)
            if (cutPosition == weak.length - 1) {
                // 마지막 취약점과 첫 취약점 사이가 가장 큰 간격인 경우 : 원래 배열과 동일
                for (int i = 0; i < weak.length; i++) {
                    linearWeak.add(weak[i]);
                }
            } else {
                // 그 외의 경우 : 자른 다음 지점부터 끝까지 -> 처음부터 자른 지점까지
                for (int i = cutPosition + 1; i < weak.length; i++) {
                    linearWeak.add(weak[i]);
                }
                for (int i = 0; i <= cutPosition; i++) {
                    linearWeak.add(weak[i] + n);
                }
            }

            // 최소한의 친구로 모든 지점을 커버할 수 있는지 확인
            int friendIdx = dist.length - 1;  // 가장 이동거리가 긴 친구부터
            int covered = 0;  // 커버된 취약점 수
            int usedFriends = 0;  // 사용된 친구 수

            while (covered < linearWeak.size() && friendIdx >= 0) {
                usedFriends++;
                int distance = dist[friendIdx--];
                int start = linearWeak.get(covered);
                int count = 0;

                // 현재 친구가 커버할 수 있는 취약점 계산
                for (int i = covered; i < linearWeak.size(); i++) {
                    if (linearWeak.get(i) <= start + distance) {
                        count++;
                    } else {
                        break;
                    }
                }

                covered += count;
            }

            return covered == linearWeak.size() ? usedFriends : -1;
        }
    }
}
