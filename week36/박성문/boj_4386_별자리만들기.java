import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N;
	static double[] x, y;
	static PriorityQueue<Node> q;
	static class Node {
		int s,e;
		double w;
		
		Node(int s, int e, double w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
	static boolean[] visited;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	 StringTokenizer st = new StringTokenizer(br.readLine());
	 N = Integer.parseInt(st.nextToken());
	 
	 x = new double[N+1];
	 y = new double[N+1];
	 visited = new boolean[N+1];
	 
	 q = new PriorityQueue<>((o1,o2)->{
		 return Double.compare(o1.w, o2.w);
	 });
	 
	 for(int i = 1; i <= N; i++) {
		 st = new StringTokenizer(br.readLine());
		 x[i] = Double.parseDouble(st.nextToken());
		 y[i] =  Double.parseDouble(st.nextToken());
	 }
	 
	 for(int i = 2; i <= N; i++) {
		 double d = Math.sqrt(Math.pow(x[1]-x[i],2) + Math.pow(y[1]- y[i], 2));
		 q.add(new Node(1,i, d));
	 }
	 
	 int cnt = 0;
	 double answer = 0;
	 
	 visited[1] = true;
	 
	 while(!q.isEmpty()) {
		 
		 Node node = q.poll();
		 int s = node.s;
		 int e = node.e;
		 double w = node.w;
		 
		 if(visited[e])
			 continue;
		 
		 visited[e]  = true;
		 answer += w;
		 cnt++;
		 
		 for(int i = 2; i <= N; i++) {
			 if(!visited[i]) {
				 double d = Math.sqrt(Math.pow(x[e]-x[i],2) + Math.pow(y[e]- y[i], 2));
				 q.add(new Node(e,i, d));
			 }
		 }
		 
		 if(cnt == N-1)
			 break;
	 }
	 
	 System.out.printf("%.2f", answer);
  
 }

}
