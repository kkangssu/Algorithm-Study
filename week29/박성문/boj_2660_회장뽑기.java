import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// The main method must be in a class named "Main".
class Main {
	static int N;

	static int[][] map;
	static List<Integer> li;
	static int min;
	static int[] count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        
        li = new ArrayList<>();
        
        map = new int[N+1][N+1];
        
        

        for(int i = 1; i <= N; i++) {
        	for(int j = 1; j <= N; j++) {
        		if(i!=j)
        			map[i][j] =51;
         	}
        }
        
        
        while(true) {
        	
        	st = new StringTokenizer(br.readLine());
        	int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	if(n1==-1)
        		break;
        	
        	map[n1][n2]=1;
        	map[n2][n1]=1;

        }
        

        for(int k =1; k<=N; k++) {
        	for(int i = 1; i <= N; i++) {
        		for(int j = 1; j <= N; j++) {
        			map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
        		}
        	}
        }
        
        min = 51;
        count = new int[N+1];
        
        for(int i = 1; i <= N; i++) {
        	for(int j = 1; j <= N; j++) {
        		if(i==j)
        			continue;
        		count[i] = Math.max(count[i], map[i][j]);

        		
        	}
        	
    		if(min > count[i]) {
    			li.clear();
    			li.add(i);
    			min = count[i];
    		} else if(min == count[i]) {
    			li.add(i);
    		}
        	
        }
        
        System.out.println(min + " " + li.size());
        for(int i : li) {
        	System.out.print(i + " ");
        }
        
    }

}
