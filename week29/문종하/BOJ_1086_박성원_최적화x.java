import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1086_박성원_최적화x {
    static int N, K;
    static String[] arr;
    static int[] ar;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        ar = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = br.readLine();

        K = Integer.parseInt(br.readLine());

        if (K == 1) {
            System.out.println("1/1");
            return;
        }

        for (int i = 0; i < N; i++)
            ar[i] = getModulo(arr[i], K);

        dp = new long[1<<N][K];

        for (int i = 0; i < (1<<N); i++)
            Arrays.fill(dp[i], -1);

        long numerator = solve(0, 0);
        long denominator = factorial(N);

        if (numerator == 0) {
            System.out.println("0/1");
            return;
        }

        long g = gcd(numerator, denominator);

        System.out.println(numerator / g + "/" + denominator / g);
    }

    static long solve(int bit, int mod) {
        if (bit == (1<<N)-1)
            return (mod == 0) ? 1 : 0;

        if(dp[bit][mod] != -1)
            return dp[bit][mod];

        dp[bit][mod] = 0;

        for (int i = 0; i < N; i++) {
            if ((bit & (1<<i)) == 0) {
                int newMod = getNewMod(mod, i);
                dp[bit][mod] += solve(bit | (1<<i), newMod);
            }
        }

        return dp[bit][mod];
    }

    static int getNewMod(int prevMod, int idx) {
        String num = arr[idx];
        int len = num.length();

        int tenPowLen = 1;
        for (int i = 0; i < len; i++) {
            tenPowLen = (tenPowLen * 10) % K;
        }

        int result = (int)(((long)prevMod * tenPowLen) % K + ar[idx]) % K;
        return result;
    }

    static int getModulo(String numStr, int K) {
        int remainder = 0;
        char[] num = numStr.toCharArray();
        for (int i = 0; i < numStr.length(); i++)
            remainder = ((remainder * 10) % K + (num[i] - '0')) % K;

        return remainder;
    }

    static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++)
            result *= i;
        return result;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}