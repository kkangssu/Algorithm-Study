package week32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_12785_토쟁이의등굣길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        
        final int MOD = 1000007;
        // 이게 메모리는 적은데 20ms 더 느림
        // long[][] dp = new long[h+1][w+1];
        long[][] dp = new long[201][201];
        
        // 첫 번째 경로 계산: (1,1) -> (x,y)
        for (int i = 0; i <= h; i++) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = 0;
            }
        }
        
        dp[1][1] = 1;
        for (int i = 1; i <= y; i++) {
            for (int j = 1; j <= x; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
            }
        }
        
        long firstPathCount = dp[y][x];
        
        if (w == x && h == y) {
            System.out.println(firstPathCount % MOD);
        } else {
            // 두 번째 경로 계산: (x,y) -> (w,h)
            for (int i = 0; i <= h; i++) {
                for (int j = 0; j <= w; j++) {
                    dp[i][j] = 0;
                }
            }
            
            dp[y][x] = 1;
            for (int i = y; i <= h; i++) {
                for (int j = x; j <= w; j++) {
                    if (i == y && j == x) {
                        continue;
                    }
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                }
            }
            
            long secondPathCount = dp[h][w];
            System.out.println((firstPathCount * secondPathCount) % MOD);
        }
    }
}
