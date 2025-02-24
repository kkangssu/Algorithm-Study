package 강수진;

import java.util.*;
import java.io.*;

public class BOJ_9251_LCS {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int l1 = str1.length;
        int l2 = str2.length;
        int[][] dp = new int[l1+1][l2+1];

        for(int i = 0; i < l1; i++){
            for(int j = 0; j < l2; j++){
                if(str1[i] == str2[j]){
                    dp[i+1][j+1] = dp[i][j] + 1;
                }
                else{
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        System.out.println(dp[l1][l2]);
    }
}
