import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


//The main method must be in a class named "Main".
class Main {
	static int N, M;
	static boolean[][] map;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	 StringTokenizer st = new StringTokenizer(br.readLine());

	   N = Integer.parseInt(st.nextToken());
	   M = Integer.parseInt(st.nextToken());
	   
	   map = new boolean[N+1][21];
	   
	   for(int t = 0; t < M; t++) {
		   st = new StringTokenizer(br.readLine());
		   
		   int action = Integer.parseInt(st.nextToken());
		   int i = Integer.parseInt(st.nextToken());
		   int x = -1;
		   
		   if(action == 1 || action == 2) {
			   x = Integer.parseInt(st.nextToken());
		   }
		
		   
		   if(action == 1) {	   
			   map[i][x] = true;		   
		   } 
		   else if(action == 2) {  
			   map[i][x] = false;
		   } 
		   else if(action == 3) {
			   
			   for(int j = 19; j >= 1; j--) {
				   map[i][j+1] = map[i][j]; 
			   }
			   map[i][1] = false;
			   
		   } 
		   else {
			   for(int j = 2; j <= 20; j++) {
				   map[i][j-1] = map[i][j]; 
			   }
			   map[i][20] = false;
			   
		   }		   
	   }
	   
	   int answer = 0;
	   StringBuilder sb;
	   Set<String> set = new HashSet<>();
	   
	   for(int i = 1; i <= N; i++) {
		   sb = new StringBuilder();
		   for(int j = 1; j <= 20; j++) {
			   if(map[i][j]) {
				   sb.append(1);
			   } else {
				   sb.append(0);
			   }
		   }
		   
		   if(!set.contains(sb.toString())) {
			   set.add(sb.toString());
			   answer++;
		   }
	   }
	   
	   System.out.println(answer);
	   
	   
 }

}
