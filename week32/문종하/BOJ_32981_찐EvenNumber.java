import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_32981_찐EvenNumber {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++)
            sb.append(solve(Integer.parseInt(br.readLine()))).append("\n");

        System.out.println(sb);
    }

    static long solve(int n) {
        if (n == 1)
            return 5;

        long result = 4;
        long base = 5;
        long exponent = n - 1;

        long power = modPow(base, exponent, MOD);

        return (result * power) % MOD;
    }

    // 모듈러 지수 계산 (a^b mod m)
    static long modPow(long base, long exponent, int modulus) {
        if (exponent == 0) return 1;

        long result = 1;
        base = base % modulus;

        while (exponent > 0) {
            // 지수가 홀수면 현재 결과에 base를 한 번 더 곱함
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }

            // 지수를 절반으로 나누고 base를 제곱
            exponent = exponent >> 1; // exponent /= 2
            base = (base * base) % modulus;
        }

        return result;
    }
}