import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N;
	static List<Integer> white;
	static List<Integer> black;
	static int[][] dp;
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
 

     white = new LinkedList<>();
     black = new LinkedList<>();
     while(true) {
    	String tmp = br.readLine();
    	if(tmp == "" || tmp == null)
    		break;
     	StringTokenizer st = new StringTokenizer(tmp);
     	int w = Integer.parseInt(st.nextToken());
     	int b = Integer.parseInt(st.nextToken());
     	white.add(w);
     	black.add(b);
     }

     int size = white.size();
     dp = new int[16][16];

     for(int i = 0; i < size; i++) {	 
    	 for(int w = 15; w >= 0; w--) {   		 
    		 for(int b = 15; b >= 0; b--) {
    			 if(w != 0) {
    				 dp[w][b] = Math.max(dp[w][b], dp[w-1][b] + white.get(i));
    			 }
    			 if(b!= 0) {
        			 dp[w][b] = Math.max(dp[w][b], dp[w][b-1] + black.get(i));
    			 }
    		 }   		 
    	 }
     }
     
    System.out.println(dp[15][15]);
 }

}
