import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N,C;
    static int[] list;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        list = new int[N];
        for(int i = 0; i < N ; i++){
        st = new StringTokenizer(br.readLine());
            list[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(list);
        int left = 1;
        int right = list[N-1] - list[0] +1;
        while(left < right){
            int mid = (left+right)/2;
            if(calc(mid) < C){ // 현재 집 갯수가 주어진 조건의 집 갯수 크다? -> 
                right= mid;
            }else{
                left = mid+1;
            }
        }
        System.out.println(left-1);
    }
    static int calc(int dist){
        int cnt = 1;
        int last = list[0];
        for(int i = 1; i < N ;i++){
            int now = list[i];
            if(now - last >= dist){
                cnt++;
                last = now;
            }
        }
        return cnt;
    }
}