import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    ex 1 - 7

    001     1       1       1
    010             2
    011     3       4       2
    100             5
    101             7
    110             9
    111     7       12      3
    ...
    1100    12      22
    1101    13
    1110    14
    1111    15      32
    ...
    11111   31
    ...
    111111  63

    마지막까지의 개수 - 시작전까지의 개수

*/
public class BOJ_9527_1의개수세기2 {
    static long [] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new long[55];
        dp[1] = 1;
//        dp[2] = 4;
//        dp[3] = 12;
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        System.out.println("A = " + A + " B = " + B);

        // 각 자리수 찾기
        int dA = getBinaryDigits(A);
        int dB = getBinaryDigits(B);
        System.out.println("dA = " + dA + " dB = " + dB);

        // 자릿 수 대비 이진수 갯수 미리 계산
        calc(dB);
        System.out.println("calc");
        
        // 정확한 1의 개수 계산
        long fA = A==1?0:find(A-1, dA);
        long fB = find(B, dB);
        System.out.println("fA = " + fA + " fB = " + fB);

        System.out.println(fB-fA);
    }
    static int getBinaryDigits(long num) {
        return 64 - Long.numberOfLeadingZeros(num);
    }
    // 자릿 수 대비 이진수 갯수
    static long calc(int digit) {
        if(dp[digit]==0) {
            int d1 = digit-1;
            dp[digit] = (calc(d1) << 1) + (1 << d1);  //+ 1 + ((1<<digit)-1);
        }
        return dp[digit];
    }
    static long find(long num, int digit) {
        long cur = 1 << digit;
        long cnt = dp[digit];
        while(--cur!=num)
            cnt -= Long.bitCount(cur);
        return cnt;
    }
}
