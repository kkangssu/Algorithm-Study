class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 0;
        int right = 200000000;
        binary: while(left < right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for(int i=0; i < stones.length; i++) {
                if(stones[i] < mid) {
                    cnt++;
                    if(cnt >= k) {
                        right = mid;
                        continue binary;
                    }
                } else cnt = 0;
            }
            left = mid + 1;
        }
        
        return left - 1;
    }
}
