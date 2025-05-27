import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] ability = new int[1000][2];
        int n = 0;
        int max = 0;

        String str = br.readLine();
        while(str  != null){
            st = new StringTokenizer(str);
            ability[n][0] = Integer.parseInt(st.nextToken()); // w
            ability[n][1] = Integer.parseInt(st.nextToken()); // b
            str = br.readLine();
            n++;
        }
        int[][][] dp = new int[n][16][16];

        dp[0][1][0] = ability[0][0]; // w
        dp[0][0][1] = ability[0][1]; // b

        for(int idx = 1; idx < n; idx++){
            for(int w = 0; w <= 15; w++){
                for(int b = 0; b <= 15; b++){
                    int pickW = 0; int pickB = 0;
                    // 백 플레이어 w명, 흑 플에이어 b명을 뽑는 방법
                    // idx번 사람을 백 플레이어로 뽑기
                    if(w > 0) pickW = dp[idx-1][w-1][b] + ability[idx][0];
                    // idx번 사람을 흑 플레이어로 뽑기
                    if(b > 0) pickB = dp[idx-1][w][b-1] + ability[idx][1];
                    // 안뽑는 경우, 백으로 뽑는 경우, 흑으로 뽑는 경우 중 MAX 찾기
                    dp[idx][w][b] = Math.max(dp[idx-1][w][b], Math.max(pickW, pickB));

                    if(w == 15 && b == 15){
                        max = Math.max(max, dp[idx][w][b]);
                    }
                }
            }
        }

        System.out.println(max);
    }
}
