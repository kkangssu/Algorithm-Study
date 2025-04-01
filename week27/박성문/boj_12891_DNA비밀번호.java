import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// The main method must be in a class named "Main".
public class Main {
    
	static int P,S;
	static char[] map;
	static int answer;
	static int[] dna;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        map = br.readLine().toCharArray();
        dna = new int[4];
        
        st= new StringTokenizer(br.readLine());
        
        int a = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        
        answer = 0;
        for(int i =0 ; i < S; i++) {
        	if(map[i] == 'A') {
        		dna[0]++;
        	}
        	if(map[i] == 'C') {
        		dna[1]++;
        	}
        	if(map[i] == 'G') {
        		dna[2]++;
        	}
        	if(map[i] == 'T') {
        		dna[3]++;
        	}
        	
        	if(i >= P-1) {
        		
        		if(dna[0] >= a && dna[1] >= c && dna[2] >= g && dna[3] >= t)
        			answer++;
        		
        		int left = i-(P-1);
      
	        	if(map[left] == 'A') {
	        		dna[0]--;
	        	}
	        	if(map[left] == 'C') {
	        		dna[1]--;
	        	}
	        	if(map[left] == 'G') {
	        		dna[2]--;
	        	}
	        	if(map[left] == 'T') {
	        		dna[3]--;
	        	}
        	}
        }

        
        

        
        System.out.println(answer);
  
        
    }
    
}
