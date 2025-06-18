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
public class BOJ_9527_1의개수세기 {
    static long [] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new long[55];

        // DP 초기화 (정답 방식으로 변경)
        setDp(); // 누적합 계산!
//        dp[1] = 1;
//        dp[2] = 4;
//        dp[3] = 12;
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        // 각 자리수 찾기
        int dA = getBinaryDigits(A);
        int dB = getBinaryDigits(B);

        // 1의 개수
        long fA = A==1?0: find(A-1);
        long fB = find(B);

        System.out.println(fB-fA);
    }

    static int getBinaryDigits(long num) {
        return 64 - Long.numberOfLeadingZeros(num);
    }
    static void setDp() {
        dp[0] = 1;
        for(int i=1;i<55;i++)
            dp[i] = (dp[i-1] << 1) + (1L << i);
    }

    static long find(long N) {
        if (N <= 0) return 0;

        long count = N & 1;

        int size = (int) (Math.log(N)/Math.log(2));

        for(int i=size;i>0;i--) {
            if((N & (1L<<i)) != 0L) {
                count += dp[i-1] +(N - (1L<<i) + 1);
                N -= (1L << i);
            }
        }
        return count;
    }
}