import java.util.*;

class PROGRAMMERS_이모티콘할인행사_실패 {
    public int[] PROGRAMMERS_이모티콘할인행사_실패(int[][] users, int[] emoticons) {
        // 할인율은 10%, 20%, 30%, 40% 중 하나로 설정
        int[] discountRates = {10, 20, 30, 40};

        // 가능한 모든 할인율 조합을 시도
        int[] answer = new int[2]; // [이모티콘 플러스 서비스 가입자 수, 이모티콘 판매액]
        int[] discounts = new int[emoticons.length];

        findBestResult(0, users, emoticons, discountRates, discounts, answer);

        return answer;
    }

    private void findBestResult(int depth, int[][] users, int[] emoticons, int[] discountRates,
                                int[] discounts, int[] answer) {
        // 모든 이모티콘에 대한 할인율 설정이 완료되면 결과 계산
        if (depth == emoticons.length) {
            calculateResult(users, emoticons, discounts, answer);
            return;
        }

        // 현재 이모티콘에 가능한 모든 할인율 적용
        for (int rate : discountRates) {
            discounts[depth] = rate;
            findBestResult(depth + 1, users, emoticons, discountRates, discounts, answer);
        }
    }

    private void calculateResult(int[][] users, int[] emoticons, int[] discounts, int[] answer) {
        int plusSubscribers = 0;
        int totalSales = 0;

        // 각 사용자마다 계산
        for (int[] user : users) {
            int userDiscountRate = user[0]; // 사용자의 할인율 기준
            int userMaxPrice = user[1];     // 사용자의 가격 기준

            int userTotalSpend = 0;

            // 각 이모티콘에 대해 구매 여부 결정
            for (int i = 0; i < emoticons.length; i++) {
                // 사용자의 할인율 기준 이상이면 구매
                if (discounts[i] >= userDiscountRate) {
                    // 할인된 가격으로 구매
                    int discountedPrice = emoticons[i] * (100 - discounts[i]) / 100;
                    userTotalSpend += discountedPrice;
                }
            }

            // 총 구매 비용이 기준 가격을 넘으면 이모티콘 플러스 가입
            if (userTotalSpend >= userMaxPrice) {
                plusSubscribers++;
            } else {
                // 그렇지 않으면 이모티콘 구매
                totalSales += userTotalSpend;
            }
        }

        // 결과 업데이트 (이모티콘 플러스 가입자 수가 우선, 같다면 판매액이 큰 것 선택)
        if (plusSubscribers > answer[0] || (plusSubscribers == answer[0] && totalSales > answer[1])) {
            answer[0] = plusSubscribers;
            answer[1] = totalSales;
        }
    }

    // 테스트
    public static void main(String[] args) {
        PROGRAMMERS_이모티콘할인행사_실패 PROGRAMMERS_이모티콘할인행사_실패 = new PROGRAMMERS_이모티콘할인행사_실패();

        // 테스트 케이스 1
        int[][] users1 = {{40, 10000}, {25, 10000}};
        int[] emoticons1 = {7000, 9000};
        int[] result1 = PROGRAMMERS_이모티콘할인행사_실패.PROGRAMMERS_이모티콘할인행사_실패(users1, emoticons1);
        System.out.println("테스트 케이스 1 결과: [" + result1[0] + ", " + result1[1] + "]");

        // 테스트 케이스 2
        int[][] users2 = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons2 = {1300, 1500, 1600, 4900};
        int[] result2 = PROGRAMMERS_이모티콘할인행사_실패.PROGRAMMERS_이모티콘할인행사_실패(users2, emoticons2);
        System.out.println("테스트 케이스 2 결과: [" + result2[0] + ", " + result2[1] + "]");
    }
}