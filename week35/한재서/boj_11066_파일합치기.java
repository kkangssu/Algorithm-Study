import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t=0; t < T; t++) {
            int files[];
            int K = Integer.parseInt(br.readLine());
            files = new int[K + 1];
            int dp[][];
            dp = new int[K + 1][K + 1];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            files[1] = Integer.parseInt(st.nextToken());
            for(int i=2; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                files[i] += files[i - 1];
            }


            for(int i=1; i < K; i++) {
                for(int j=1; j + i <= K; j++) {
                    int end = j + i;
                    dp[j][end] = Integer.MAX_VALUE;

                    for(int mid = j; mid < end ;mid++) {
                        dp[j][end] = Math.min(dp[j][end], dp[j][mid] + dp[mid + 1][end] + files[end] - files[j - 1]);
                    }
                }
            }
            System.out.println(dp[1][K]);
        }
    }
}
