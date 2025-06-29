import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N, M;
	static int answer;
	static boolean[][] visited;
	static int[] map;
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	 StringTokenizer st = new StringTokenizer(br.readLine());
	 N = Integer.parseInt(st.nextToken());
	 M = Integer.parseInt(st.nextToken());
	 
	 visited = new boolean[N+1][N+1];
	 
	 for(int i = 0; i < M; i++) {
		 st = new StringTokenizer(br.readLine());
		 int n1 = Integer.parseInt(st.nextToken());
		 int n2 = Integer.parseInt(st.nextToken());
		 visited[n1][n2] = true;
		 visited[n2][n1] = true;
	 }
	 
	 answer = 0;

	 map = new int[3];
	 comb(0,1);

	 System.out.println(answer);
	 
 }
 
 	static void comb(int cnt, int start) {
 		
 		if(cnt == 3) {
 			int n1 = map[0];
 			int n2 = map[1];
 			int n3 = map[2];
 			if(visited[n1][n2] || visited[n1][n3] || visited[n2][n3])
 				return;
 			
 			answer++;
 			return;
 		}
 		
 		for(int i = start; i <= N; i++) {
 			map[cnt] = i;
 			comb(cnt+1, i+1);
 		}
 		
 	}
 
}
