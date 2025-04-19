package week23.EliteZer0;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n, int[] info) {
        // 어피치 점수 계산
        int scoreA = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] > 0) {
                scoreA += 10 - i;
            }
        }

        // 라이언이 각 과녁에서 점수를 얻기 위해 필요한 화살 수 계산
        int[] arrowR = new int[11];
        for (int i = 0; i < info.length; i++) {
            arrowR[i] = info[i] + 1;
        }

        // 각 과녁의 점수, 총 점수, 필요 화살 수 계산
        List<int[]> rate = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            if (i == 10) {
                // 0점 과녁은 점수가 없으므로 제외
                continue;
            }
            int score = 10 - i;
            // 어피치가 이미 과녁에 화살을 맞춘 경우 어피치가 점수를 잃고 라이언이 가져가므로 라이언에게 2배로 점수 보상
            int totalScore = info[i] > 0 ? score * 2 : score;
            rate.add(new int[] { score, totalScore, arrowR[i] });
        }

        // 정렬: 낮은 점수의 과녁을 우선순위로 두고, 필요한 화살이 적은 것을 우선으로 함
        // 낮은 점수를 맞힌 개수가 같을 경우 그 다음으로 낮은 점수를 더 많이 맞힌 경우를 리턴하라고 문제에서 요구했기 때문에
        // 같은 점수 차이여도 낮은 점수에 더 많은 화살을 배분할 수 있도록 낮은 점수의 과녁을 우선순위로 설정
        rate.sort((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[2] - b[2];
        });

        // dp[i][0]: i개의 화살로 얻을 수 있는 최대 점수 차이
        // dp[i][1]: i개의 화살로 최대 점수 차이를 만들 때의 과녁 정보 (rate의 인덱스)
        // dp[i][2]: i개의 화살로 최대 점수 차이를 만들 때 선택된 과녁 중 제일 낮은 점수의 과녁
        int[][] dp = new int[n + 1][3];
        List<Integer>[] scoreRecords = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
            dp[i][1] = -1;
            dp[i][2] = Integer.MAX_VALUE;
            scoreRecords[i] = new ArrayList<>();
        }

        for (int i = 0; i < rate.size(); i++) {
            int score = rate.get(i)[0];
            int totalScore = rate.get(i)[1];
            int arrows = rate.get(i)[2];

            for (int x = n; x >= arrows; x--) {
                // 해당 과녁을 맞추지 않는 경우의 현재 최대 점수 차이
                int currentBest = dp[x][0];
                // 해당 과녁을 맞추는 경우의 최대 점수 차이
                int newScore = dp[x - arrows][0] + totalScore;

                // 점수 차이가 더 크거나, 점수는 같은데 더 낮은 점수의 과녁을 맞춘 경우
                if (newScore > currentBest ||
                        (newScore == currentBest && dp[x - arrows][2] < dp[x][2])) {
                    dp[x][0] = newScore;

                    // scoreRecords[n]에 n개의 화살로 최적의 점수 차이를 만들기 위해 선택한 모든 과녁의 인덱스를 저장
                    // scoreRecords[x] = scoreRecords[x - arrows];
                    // scoreRecords[x].add(i);
                    // 이렇게 작성하면 참조 복사로 인해서 값이 수정되는 문제 때문에 새 객체를 생성해서 복사
                    List<Integer> newRecord = new ArrayList<>(scoreRecords[x - arrows]);
                    newRecord.add(i); // rate 리스트의 인덱스 저장
                    scoreRecords[x] = newRecord;

                    // 인덱스 저장
                    dp[x][1] = i;

                    // 최소 점수 업데이트
                    dp[x][2] = Math.min(dp[x - arrows][2], score);
                }
            }
        }

        int[] answer = new int[11];

        if (dp[n][1] == -1) {
            return new int[] { -1 };
        }

        int remainingArrows = n;

        for (int idx : scoreRecords[n]) {
            int[] rateInfo = rate.get(idx);
            int score = rateInfo[0];
            int arrowsNeeded = rateInfo[2];

            // 과녁 인덱스 계산(10점부터 역순으로 저장했으므로)
            int targetIdx = 10 - score;

            answer[targetIdx] = arrowsNeeded;
            remainingArrows -= arrowsNeeded;
        }

        // 남은 화살은 0점에 배치
        answer[10] = remainingArrows;

        // 라이언 점수 계산
        int scoreR = 0;
        scoreA = 0;
        for (int i = 0; i < 10; i++) {
            if (answer[i] > info[i]) {
                scoreR += 10 - i;
            } else if (info[i] > 0) {
                scoreA += 10 - i;
            }
        }

        int scoreDifference = scoreR - scoreA;

        // 라이언이 이기지 못하면 -1 반환
        if (scoreDifference <= 0) {
            return new int[] { -1 };
        }

        return answer;
    }
}
