import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {

	static int N;
	static int[] map;
	static int[] dp;
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     map = new int[N+1];
     
     dp = new int[N+1];
     
     st = new StringTokenizer(br.readLine());
     for(int i = 1; i <= N; i++) {
    	 map[i] = Integer.parseInt(st.nextToken());
    	 dp[i] = 1;
     }

     int answer = 0;
     for(int i = 1; i <= N; i++) {
    	 for(int j = 0; j < i; j++) {
    		 if(map[i] < map[j]) {
    			dp[i] = Math.max(dp[i], dp[j]+1);
    		 }

    	 } 
    	 answer = Math.max(dp[i], answer);
     }
     
     System.out.println(N-answer);
     
 }
 
}
