/**
 * worst
 * 테스트 21 〉	통과 (26.69ms, 86.2MB)
 * 테스트 19 〉	통과 (21.08ms, 96.2MB)
 */


public class programmers_60062_외벽점검_완탐 {
    class Solution {
        private int[] dist;
        private int answer;
        private boolean[] visited;
        private int[] selectedFriends;

        public int solution(int n, int[] weak, int[] dist) {
            this.dist = dist;
            this.answer = Integer.MAX_VALUE;

            int weakLen = weak.length;

            // 원형을 선형으로 펼치기 (2배로 늘리기)
            int[] weakPoints = new int[weakLen * 2];
            for (int i = 0; i < weakLen; i++) {
                weakPoints[i] = weak[i];
                weakPoints[i + weakLen] = weak[i] + n;
            }

            // 백트래킹을 위한 초기화
            visited = new boolean[dist.length];
            selectedFriends = new int[dist.length];

            // 백트래킹으로 모든 친구 순열 생성
            generatePermutations(0, weakLen, weakPoints);

            // 모든 취약점을 점검할 방법이 없다면 -1 반환
            return answer == Integer.MAX_VALUE ? -1 : answer;
        }

        // 백트래킹을 이용한 순열 생성
        private void generatePermutations(int depth, int weakLen, int[] weakPoints) {
            // 모든 친구를 순서대로 선택했으면 해당 순열로 점검 시도
            if (depth == dist.length) {
                checkWeakPoints(weakLen, weakPoints);
                return;
            }

            for (int i = 0; i < dist.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    selectedFriends[depth] = dist[i];
                    generatePermutations(depth + 1, weakLen, weakPoints);
                    visited[i] = false;
                }
            }
        }

        // 현재 생성된 순열(selectedFriends)로 취약점 점검이 가능한지 확인
        private void checkWeakPoints(int weakLen, int[] weakPoints) {
            // 시작점 선택 (모든 취약점을 시작점으로 시도)
            for (int start = 0; start < weakLen; start++) {
                int friendCount = 0;  // 투입한 친구 수
                int position = weakPoints[start];  // 첫 번째 취약점에서 시작

                // 첫 번째 친구 배치
                friendCount++;
                if (friendCount > dist.length) {
                    continue;  // 친구가 부족하면 다음 시작점 시도
                }
                position = position + selectedFriends[friendCount - 1];

                // 나머지 취약점 점검
                boolean covered = true;
                for (int i = 1; i < weakLen; i++) {
                    int weakIndex = start + i;

                    // 현재 친구로 커버할 수 없는 거리면 다음 친구 투입
                    if (weakPoints[weakIndex] > position) {
                        friendCount++;

                        // 더 이상 친구가 없거나 이미 최소값보다 많은 친구를 사용했으면 중단
                        if (friendCount > dist.length || friendCount >= answer) {
                            covered = false;
                            break;
                        }

                        // 다음 친구가 커버할 수 있는 거리 계산
                        position = weakPoints[weakIndex] + selectedFriends[friendCount - 1];
                    }
                }

                // 모든 취약점을 점검할 수 있다면 최소값 갱신
                if (covered && friendCount <= dist.length) {
                    answer = Math.min(answer, friendCount);
                }
            }
        }
    }
}