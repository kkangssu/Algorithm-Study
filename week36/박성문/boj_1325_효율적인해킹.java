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
	static List<Integer> answer;
	static boolean[] visited;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws Exception {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	   StringTokenizer st = new StringTokenizer(br.readLine());

	   N = Integer.parseInt(st.nextToken());
	   M = Integer.parseInt(st.nextToken());

	   li = new List[N+1];
	   
	   for(int i = 1; i <= N; i++) {
		   li[i] = new ArrayList<>();
	   }
	   
	   for(int i = 0; i < M; i++) {
		   st = new StringTokenizer(br.readLine());
		   int A = Integer.parseInt(st.nextToken());
		   int B = Integer.parseInt(st.nextToken());
		   
		   li[B].add(A);
	   }
	  
   
	   int max = -1;
	   answer = new ArrayList<>();
	   q = new LinkedList<>();
	   
	   for(int i = 1; i <=N; i++) {
		   
		   visited = new boolean[N+1];
		   
		   q.add(i);
		   int cnt =  1;
		   visited[i] = true;
		   
		   while(!q.isEmpty()) {
			   
			   int n = q.poll();
			   
			   for(int m : li[n]) {
				   if(!visited[m]) {
					   visited[m] = true;
					   cnt++;
					   q.add(m);
				   }
			   }
			   
		   }

		   if(cnt > max) {
			   max = cnt;
			   answer.clear();
			   answer.add(i);
		   } else if (cnt ==max) {
			   answer.add(i);
		   }
		   
	   }
	   
	   StringBuilder sb = new StringBuilder();
	   
	   for(int i : answer) {
		   sb.append(i).append(" ");
	   }
	
	   System.out.println(sb.toString());
	   
	}	
}
