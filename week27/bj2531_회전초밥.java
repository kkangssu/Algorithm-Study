import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, d, k ,c ; //접시 , 초밥가지, 연속접시, 쿠폰번호
    static int[] susis;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        susis = new int[N];
        for(int i = 0; i < N ; i++){
            susis[i] = Integer.parseInt(br.readLine());
        }
      
        int start = 0;
        int end = k-1;
      
        Map<Integer, Integer> check = new HashMap<>();
        check.put(c,30001); // 쿠폰은 무조건 존재
        for(int i = 0 ; i <= end; i++){ //end를 k로 두고 i<end로 했더니 while문에서 window 업데이트 후에 들어올걸 더해서, k가 1번씩 앞서는 현상 발생, 그래서 k-1로 하고 i<=end로 수정
            check.merge(susis[i], 1, (o,n)-> o + n);
        }
      
        int res = check.size();
        
        while(true){

            // 나갈 거 빼기
            int out = susis[start];
            if(check.get(out) -1 == 0){
                check.remove(out);
            }else{
                check.put(out, check.get(out)-1);
            }
            // 윈도우 업데이트
            start = (start+1)%N;
            if(start==0){
                break;
            }
            end = (end+1)%N;

            // 들어올거 더하기
            int in = susis[end];
            check.merge(in,1,(o,n)->o+n);

            // 최댓값 확인
            res= Math.max(res,check.size());
        }
        System.out.println(res);
    }
}
