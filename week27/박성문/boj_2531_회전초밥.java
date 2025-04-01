import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

// The main method must be in a class named "Main".
public class Main {
    
	static int N,d,k,c;
	static int[] map;
	static int answer;
	static int[] count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
       
        map = new int[N];

        for(int i = 0; i < N; i++) {
        	map[i] = Integer.parseInt(br.readLine());
        	
        }
        
        count= new int[3001];
        

    	  count[c]=1;
        int cnt = 1;
        
        for(int i = 0; i < k;i++) {
        	count[map[i]]++;
        	if(count[map[i]] == 1) {
        		cnt++;
        	}
        }
        
        answer = cnt;
     
        for(int i = 1; i < N; i++) {
        	count[map[i-1]]--;
        	if(count[map[i-1]] == 0)
        		cnt--;
        	
        	int right = (i+k-1)%N;
        	if(count[map[right]] == 0)
        		cnt++;
        	count[map[right]]++;
        	
        	answer=Math.max(answer, cnt);
        	
        }
        
        System.out.println(answer);
    
    }
    
}
