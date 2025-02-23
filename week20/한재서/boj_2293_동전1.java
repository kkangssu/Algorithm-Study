import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] answer = new int[K + 1];
        answer[0] = 1;
        int[] coins = new int[N];
        for(int i=0; i < N; i++) coins[i] = Integer.parseInt(br.readLine());

        for(int coin : coins) {
            for(int i=coin; i < K + 1; i++) {
                answer[i] += answer[i - coin];
            }
        }

        System.out.println(answer[K]);
    }
}
