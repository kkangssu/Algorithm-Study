class Solution {
    public int solution(int[] stones, int k) {
        
        int left = 1;
        int right = 200000000;
        int answer = Integer.MAX_VALUE;
        while(left <= right){
            
            int mid = (left + right)/2;
            int cnt = 0;
            for(int i = 0; i < stones.length; i++){
                if(mid >= stones[i]){
                    cnt++;
                } else{
                    cnt = 0;
                }
                if(cnt >= k){
                    break;
                }
            }
            
            if(cnt >= k){
                right = mid-1;
            } else{
                left = mid+1;
            }
            
        }

        
        return left;
    }
}
