import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// The main method must be in a class named "Main".
class Main {
	static int N;
	static int[][] map;
	static int[][] dp;
	static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		
        	}
        }
        
        dp = new int[N][(1<<N)-1];
        
        for(int i = 0; i < N; i++)
        	Arrays.fill(dp[i], -1);
        
        System.out.println(check(0,1));
    
    }
    
    static int check(int s, int e) {
    	
    	if(e == (1<<N)-1) {
    		return map[s][0];
    	}
    	
    	if(dp[s][e] != -1)
    		return dp[s][e];
    	
    	dp[s][e]= 1000000*16+1;
    	
    	for(int i = 0; i < N; i++) {
    		if((e & (1<<i)) == 0 && map[s][i] != 0) {
    			dp[s][e] = Math.min(check(i, e | (1<<i)) + map[s][i], dp[s][e]);
    		}
    	}
    	
    	return dp[s][e];
    	
    	
    }

}
