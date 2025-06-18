import java.io.BufferedReader;
import java.io.InputStreamReader;

//The main method must be in a class named "Main".
class Main {

 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     char[] tmp = br.readLine().toCharArray();
     
     if(tmp[0] == '0') {
    	 System.out.println(0);
    	 return;
     }
     
     int N = tmp.length;
     int[] map = new int[N+1];
     int[] dp = new int[N+1];
     for(int i = 0; i < N; i++) {
    	 map[i+1] = tmp[i] - '0';
     }

     dp[0] = 1;
     
     for(int i = 1; i <= N; i++) {
    	 if(map[i-1] == 1) {
    		 dp[i] += (dp[i-2])%1000000;
    	 } else if(map[i-1] == 2 && map[i] < 7) {
    		 dp[i] += (dp[i-2])%1000000;
    	 }
    	 if(map[i] != 0)
    		 dp[i] += dp[i-1]%1000000;
     }
     
     System.out.println(dp[N]%1000000);

     
 }

}
