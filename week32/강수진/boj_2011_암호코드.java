import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if (str.length() == 0 || str.charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        
        int[] dp = new int[str.length()];
        dp[0] = 1;
        for(int i = 1; i < str.length(); i++){
            int one = str.charAt(i) - '0';
            int two = str.charAt(i-1) - '0';
            int n = two*10 + one;
            if(one == 0){
                if(n >= 10 && n <= 26) {
                    if(i == 1) dp[i] = 1;
                    else dp[i] = dp[i-2];
                }
                else {
                    System.out.println(0);
                    return;
                }
            }
            else{
                if(n >= 10 && n <= 26){
                    if(i == 1) dp[1] = 2;
                    else dp[i] = (dp[i-1] + dp[i-2])%1000000;
                }
                else dp[i] = dp[i-1];
            }   
            //System.out.println(i+": " + dp[i]);
        }
        System.out.println(dp[str.length()-1]);
    }
}
