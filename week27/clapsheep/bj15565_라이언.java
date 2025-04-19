import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int cnt = 0;
        int res = Integer.MAX_VALUE;
        for (int end = 0; end < N ; end++) {
           if(arr[end] == 1){
               cnt++;
           } 
           while(cnt >= K){
               res = Math.min(res, end-start+1);
               if(arr[start++]== 1){
                   cnt--;
               }
           }
        }
        if(res == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(res);    
        }
        
    }
}
