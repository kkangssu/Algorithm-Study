package week32;

import java.io.*;

public class boj_32981_찐EvenNumber {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            int N = Integer.parseInt(br.readLine());
            if (N == 1) {
                System.out.println(5);
            } else {
                // 첫째자리 2,4,6,8 나머지 2,4,6,8,0 중 하나
                // 정확도 때문에 거듭제곱 계산 구현현
                long result = (4 * pow(5, N - 1)) % MOD;
                System.out.println(result);
            }
        }
    }

    static long pow(long base, int exp) {
        if (exp == 0) return 1;
        long half = pow(base, exp / 2);
        long result = (half * half) % MOD;
        if (exp % 2 == 1) {
            result = (result * base) % MOD;
        }
        return result;
    }
}