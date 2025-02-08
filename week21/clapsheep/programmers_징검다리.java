import java.util.*;

class Solution {
    static int[] list;
    static int N,K;
    public long solution(int[] stones, int k) {
        list = stones;
        K = k;
        N = stones.length;
       
        
        // 파라메트릭 서치로 최적의 건널 인원 찾기
        int left = 1;
        int right =0;
        for(int i=0; i < N ; i++){
            right = Math.max(right, list[i]);
        }
        while(left <= right){
            int mid = (left+right)/2;
            
            if(isCan(mid)) {
                left = mid + 1;  // 건널 수 있으면 더 큰 값 시도
            } else {
                right = mid - 1;  // 건널 수 없으면 더 작은 값 시도
            }
        }
        return left;
    }
    public boolean isCan(int p){
        int length = 0;
        int temp = 0;
        for(int i=0; i < N ; i++){
            if((list[i] - p)<=0){
                temp++;
            }else{
                length = Math.max(length, temp);
                temp = 0;
            }
        }
        length = Math.max(length, temp);
        return length < K;
        
        
    }
}
