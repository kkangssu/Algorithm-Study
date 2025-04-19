class Solution {
    int[] discounts = {10, 20, 30, 40};
    int maxSubscribers = 0;
    int maxSales = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        // 각 이모티콘별 할인율을 결정하기 위한 재귀 호출
        dfs(0, new int[emoticons.length], users, emoticons);
        
        return new int[]{maxSubscribers, maxSales};
    }
    
    private void dfs(int depth, int[] discountRates, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            calculate(discountRates, users, emoticons);
            return;
        }
        
        // 각 이모티콘에 대해 가능한 모든 할인율 적용
        for (int discount : discounts) {
            discountRates[depth] = discount;
            dfs(depth + 1, discountRates, users, emoticons);
        }
    }
    
    private void calculate(int[] discountRates, int[][] users, int[] emoticons) {
        int subscribers = 0;
        int totalSales = 0;
        
        // 각 사용자별 구매 계산
        for (int[] user : users) {
            int userDiscountRate = user[0];
            int userMaxPrice = user[1];
            int userTotalCost = 0;
            
            // 각 이모티콘에 대해 구매 여부 확인
            for (int i = 0; i < emoticons.length; i++) {
                if (discountRates[i] >= userDiscountRate) {
                    // 할인된 가격 계산 (실수 계산 후 정수로 변환)
                    int discountedPrice = (int)(emoticons[i] * (100 - discountRates[i]) / 100.0);
                    userTotalCost += discountedPrice;
                }
            }
            
            // 구독 여부 확인
            if (userTotalCost >= userMaxPrice) {
                subscribers++;
            } else {
                totalSales += userTotalCost;
            }
        }
        
        // 최대값 갱신
        if (subscribers > maxSubscribers || 
            (subscribers == maxSubscribers && totalSales > maxSales)) {
            maxSubscribers = subscribers;
            maxSales = totalSales;
        }
    }
}
