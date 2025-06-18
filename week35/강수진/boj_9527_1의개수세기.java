import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        System.out.println(countOne(b) - countOne(a-1));
    }

    // 0-n까지 1의 개수
    static long countOne(long n){
        long cnt = 0;
        // 10^16까지라서 이진수로 바꾸면 0-53자리까지만 확인하면 됨
        for(int i = 0; i < 54; i++){
            /*
                2진수로 바꿨을 때
                0번째 자리 -> 01 01 01 01... 반복 -> 범위 = 2, 범위 내 1의 개수 = 1
                1번째 자리 -> 0011 0011 0011... 반복 -> 범위 = 2, 범위 내 1의 개수 = 2
                ...
                그래서 범위 = 1 << (i+1), 범위 내 1의 개수 = 1 << i
            */
            long range = 1L << (i+1);
            long num1 = 1L << i;
            
            /*
                0011 0011 0011 0011 001까지 구하는 경우
                1. 0011 0011 0011 0011까지의 1의 개수 구하기 = (n+1) / 범위 * 범위 내 1의 개수
                2. 남은 자리 구하기 -> 범위의 반은 0, 나머지 반은 1, 그래서 남은 숫자가 3개면 앞의 2개는 0이고 뒤의 하나는 1
            */
            // 1번
            cnt += ((n+1)/range) * num1;

            // 2번
            long remain = (n+1) % range;
            long half = range >> 1L;
            if(remain > half) cnt += remain - half;            
        }

        return cnt;
    }
} 
