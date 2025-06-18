import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int q = Integer.parseInt(br.readLine());
        long[] dp= new long[10000001];
        int max = 1;

        dp[1]=4;
        for(int i=0; i<q;i++){
            int n=Integer.parseInt(br.readLine());
            if(n==1) sb.append(5).append("\n");
            else if(max >= n) sb.append(dp[n]).append("\n");
            else{
                for(int j=max+1;j <n+1;j++){
                    dp[j]=(dp[j-1]*5)%1000000007;
                }
                sb.append(dp[n]).append("\n");
                max=n;
            }
        }
        System.out.println(sb);
    }
}
