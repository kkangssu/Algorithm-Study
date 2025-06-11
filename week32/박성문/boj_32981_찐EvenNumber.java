import java.io.BufferedReader;
import java.io.InputStreamReader;

//The main method must be in a class named "Main".
class Main {

 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     int Q = Integer.parseInt(br.readLine());
     int max = (int)Math.pow(10, 7);

     long[] dp = new long[max+1];
     
     dp[1] = 5;
     dp[2] = 20;
     
     for(int i = 3; i <= max; i++) {
    	dp[i] = (long) (((dp[i-1]) * 5)%(Math.pow(10, 9)+7));
     }
     
     StringBuilder sb = new StringBuilder();
     for(int q = 0; q < Q; q++) {
    	 int n = Integer.parseInt(br.readLine());
    	 
    	 sb.append(dp[n]).append("\n");
    	 
     }
     
     System.out.println(sb.toString());
     
 }

}
