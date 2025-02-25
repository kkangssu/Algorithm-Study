import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static String a, b;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        int aCnt = a.length();
        int bCnt = b.length();
        int[][] dp = new int[aCnt+1][bCnt+1];
        for(int i = 1; i <= aCnt; i++){
            for(int j = 1; j <=bCnt; j++){
                if(a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);   
                }
            }
        }
        System.out.println(dp[aCnt][bCnt]);
        
    }
}