import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int res = Integer.MAX_VALUE;
        int L = 0;
        int R = 0;
        while(R < N){
            int cur = arr[R] - arr[L];
            if(cur > M && cur < res) res = cur;
            
            if(cur < M){
                R++;
            }else if(cur > M){
                L++;
            }else{
                System.out.println(M);
                return;
            }
            
        }
        System.out.println(res);
    }
}
