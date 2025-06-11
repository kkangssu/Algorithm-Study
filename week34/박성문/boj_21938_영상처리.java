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
	static int T;
	static int[][] map;
	static int answer;
	static boolean[][] visited;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     M = Integer.parseInt(st.nextToken());
     
     map = new int[N][M];
     visited = new boolean[N][M];

 
	 for(int i = 0; i < N; i++) {
		st = new StringTokenizer(br.readLine());

		for(int j = 0; j < M; j++) {
			for(int k = 0; k < 3; k++) {
			map[i][j] += Integer.parseInt(st.nextToken());
				if(k==2) {
					map[i][j] /= 3;
				}
			}
		}
	 } 
     
     T = Integer.parseInt(br.readLine());
     answer = 0;
     
     for(int i = 0; i < N; i++) {
    	 for(int j = 0; j < M; j++) {
    		 if(!visited[i][j] && map[i][j] >= T) {
    			 answer++;
    			 dfs(i,j);
    		 }
    	 }
     }
     
     System.out.println(answer);
 }
 	
 	static void dfs(int r, int c) {
 		
 		visited[r][c] = true;
 		
 		for(int d = 0; d< 4; d++) {
 			int nr = r + dr[d];
 			int nc = c + dc[d];
 			
 			if(check(nr,nc) || visited[nr][nc] || map[nr][nc] < T)
 				continue;
 			
 			dfs(nr,nc);
 		}
 		
 	}
 	
 	static boolean check(int r, int c) {
 		return r>= N || r < 0 || c>=M || c< 0;
 	}
}

