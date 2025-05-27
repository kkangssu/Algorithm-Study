import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static final int MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            int N = Integer.parseInt(br.readLine());
            if (N == 1) {
                sb.append(5).append("\n");
            } else {
                long power = fastPow(5, N - 1);
                long result = (4L * power) % MOD;
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    static long fastPow(long base, int exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
}
