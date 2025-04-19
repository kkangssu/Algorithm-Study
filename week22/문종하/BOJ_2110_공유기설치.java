package w22_20250225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_공유기설치 {
    static int N, K;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        System.out.println(bs());

    }
    static int bs(){
        int l = 1 ;
//        int r = 1_000_000_000/K;
        int r = arr[N-1] - arr[0] + 1;
        int mid = 0;
        int ans = 1;
        while(l<r){
            mid = (l+r)/2;
            if(check(mid)){
                l = mid+1;
                ans = mid;
            }else{
                r = mid;
            }
        }

        return ans;
    }
    static boolean check(int n){
        int cnt = 1;
        int cur = 0;
        for(int i = 1; i < N ; i++){
            if(arr[i]-arr[cur]>=n){
                cnt++;
                cur = i;
            }

            if(cnt == K)break;
        }

        return cnt == K;
    }
}
