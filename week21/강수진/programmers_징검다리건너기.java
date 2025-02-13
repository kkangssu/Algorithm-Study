package week21.강수진;

class programmers_징검다리건너기 {
    public int solution(int[] stones, int k) {
        int right = Integer.MIN_VALUE;
        int left = Integer.MAX_VALUE;

        for(int i = 0; i < stones.length; i++){
            right = Math.max(right, stones[i]);
            left = Math.min(left, stones[i]);
        }
        int mid = 0;
        while(left <= right){
            mid = (left+right)/2;
            if(cross(mid, stones, k)){
                left = mid+1;
            }
            else right = mid-1;
        }

        return right+1;
    }
    static boolean cross(int mid, int[] stones, int k){
        int cnt = 0;
        for(int i = 0; i < stones.length; i++){
            if(stones[i]-mid <= 0) cnt++;
            else cnt = 0;

            if(cnt >= k) return false;
        }
        return true;
    }
}