package w22_20250225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_9251_LCS {
    static char[] c1;
    static char[] c2;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // A - Z => 65 - 90
        c1 = br.readLine().toCharArray();
        c2 = br.readLine().toCharArray();

        /*
            ACAYKP
            CAPCAK

            1. A    O
            2. AC   O
            3. ACA  O
            4. ACAY X
            5. ACAK O
            6. ACAKP X
            C
            CA
            CAY
            CAK
            CAKP


        */
        int [][] dp = new int[c1.length+1][c2.length+1];

        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                if (c1[i-1] == c2[j-1]) {
                    dp[i][j]=dp[i-1][j-1]+1;
                }else
                    dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
//        for (int i = 0; i <= c1.length; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        System.out.println(dp[c1.length][c2.length]);
    }
}
