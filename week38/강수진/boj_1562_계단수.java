import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1562_계단수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n < 10){
            System.out.println("0");
            return;
        }

        int[][][] dp = new int[n][10][1 << 10];
        for(int i = 1; i < 10; i++){
            dp[0][i][1 << i] = 1;
        }

        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < (1 << 10); k++){
                    if(dp[i][j][k] == 0) continue;
                    // i번째 자리에 j가 들어간게 현재 숫자
                    if(j < 9){
                        // i+1번째 자리에 j+1이 들어갈 수 있는 경우
                        int nextK = k | (1 << j+1);
                        dp[i+1][j+1][nextK]  = (dp[i][j][k] + dp[i+1][j+1][nextK]) % 1000000000;
                    }
                    if(j > 0){
                        // i+1번째 자리에 j-1이 들어갈 수 있는 경우
                        int nextK = k | (1 << j-1);
                        dp[i+1][j-1][nextK] = (dp[i][j][k] + dp[i+1][j-1][nextK]) % 1000000000;
                    }
                }
            }
        }

        int result = 0;
        for(int i = 0; i < 10; i++){
            result = (result + dp[n-1][i][(1 << 10) - 1]) % 1000000000;
        }

        System.out.println(result);
    }
}
