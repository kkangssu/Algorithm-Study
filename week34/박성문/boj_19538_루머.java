import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {

	static int N, M;
	static List<Integer>[] li;	
	static int[] count;
	static int[] answer;
	static boolean[] visited;
	static Queue<Integer> q;
	static int[] rumor;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     
     li = new List[N+1];
     count = new int[N+1];
     rumor = new int[N+1];
     answer = new int[N+1];
     visited = new boolean[N+1];
     
     for(int i = 1; i <= N; i++) {
    	 st = new StringTokenizer(br.readLine());
    	 li[i] = new ArrayList<>();
    	 
    	 while(st.hasMoreTokens()) {
    		 int n = Integer.parseInt(st.nextToken());
    		 if(n != 0) {
    			 count[n]++;
    			 li[i].add(n);
    		 }
    	 }
    	 answer[i] = -1;
     }
     
     M = Integer.parseInt(br.readLine());
     
     q = new LinkedList<>();
     st = new StringTokenizer(br.readLine());
     for(int i = 0; i < M; i++) {
    	 int n = Integer.parseInt(st.nextToken());
    	 q.add(n);
     }
     
     int cnt = 0;
     
     while(!q.isEmpty()) {
    	 int size = q.size();
    	 
    	 for(int i = 0; i < size; i++) {
    		 int n = q.poll();
    		 if(visited[n])
    			 continue;
    		 
    		 answer[n] = cnt;
			 visited[n] = true;
			 
    		 for(int j : li[n]) {
    			 rumor[j]++;
    			 if(!visited[j] && rumor[j] >= ((double)count[j]/2)) {
    				 q.add(j);
    			 }			
    		 }
    		 
    	 }
    	 cnt++;
     }
     
     StringBuilder sb= new StringBuilder();
     
     for(int i = 1; i <= N; i++) {
    	 sb.append(answer[i]).append(" ");
     }
     
     System.out.println(sb.toString());
    
 }
 
 
}

