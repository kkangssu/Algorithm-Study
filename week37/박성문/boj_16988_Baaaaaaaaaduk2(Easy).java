import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N, M;
	static int answer;
	static boolean[][] visited;
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static List<int[]> li;
	static int size;
	static int[] tmp;
	static Queue<int[]> q,q2;
	static Map<Integer, Integer> hashMap;
	static boolean[] isAble;
	static int newIndex;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	 StringTokenizer st = new StringTokenizer(br.readLine());
	 N = Integer.parseInt(st.nextToken());
	 M = Integer.parseInt(st.nextToken());
	 map = new int[N][M];
	 li = new ArrayList<>();
	 for(int i = 0; i < N; i++) {
		 st = new StringTokenizer(br.readLine());
		 for(int j = 0; j < M; j++) {
			 map[i][j] = Integer.parseInt(st.nextToken());
			 if(map[i][j] == 0) {
				 li.add(new int[] {i,j});
			 }
		 }
	 }
	 	 
	 size = li.size();
	 tmp = new int[2];
	 q = new LinkedList<>();
	 q2 = new LinkedList<>();
	 hashMap = new HashMap<>();
	 
	 visited = new boolean[N][M];
	 newIndex = 3;
	 
	 for(int i = 0; i < N; i++) {
		 for(int j = 0; j < M; j++) {
			 if(map[i][j] == 2) {
				 q.add(new int[] {i,j});
				 visited[i][j] = true;
				 int count = 0;
				 while(!q.isEmpty()) {
					 int[] idx = q.poll();
					 int r = idx[0];
					 int c = idx[1];
					 
					 map[r][c] = newIndex;
					 count++;
	 				for(int d = 0; d < 4; d++) {
	 					int nr = r+dr[d];
	 					int nc = c+dc[d];
		
	 					if(check(nr,nc) || visited[nr][nc])
	 						continue;
	 					if(map[nr][nc] == 2) {
	 						q.add(new int[] {nr,nc});
	 						visited[nr][nc] = true;
	 					}
	 				}
					 
				 }
				 hashMap.put(newIndex++, count);
			 }
		 }
	 }
	 
	 if(hashMap.size() == 0) {
		 System.out.println(0);
		 return;
	 }
	 baduk(0,0);
	 System.out.println(answer);
	 
 }
 	static void baduk(int start, int cnt) {
 		q.clear();
 		q2.clear();
 		
 		if(cnt == 2) {
 			visited = new boolean[N][M];
 			isAble = new boolean[newIndex];
 			int count = 0;
 			
 			for(int i = 0; i < 2; i++) {
 				int n = tmp[i];
 				int r = li.get(n)[0];
 				int c = li.get(n)[1];
 				map[r][c] = 1;
 				q.add(new int[] {r,c});
 				visited[r][c] = true;
 			}
 			
 			while(!q.isEmpty()) {
 				
 				int[] idx = q.poll();
 				int r = idx[0];
 				int c = idx[1];
 				
 				for(int d = 0; d < 4; d++) {
 					int nr = r+dr[d];
 					int nc = c+dc[d];
	
 					if(check(nr,nc) || visited[nr][nc])
 						continue;
 					
 					visited[nr][nc] = true;
 					
 					if(map[nr][nc] >= 2) {
 						q2.add(new int[] {nr,nc, map[nr][nc]});					
 						isAble[map[nr][nc]] = true;
 					} else if(map[nr][nc]==1) {
 						q.add(new int[] {nr,nc});
 					}
 					
 				}
 				
 			}
 				
 			visited = new boolean[N][M];
 			while(!q2.isEmpty()) { 	
 				
 				int[] idx = q2.poll();
 				int r = idx[0];
 				int c = idx[1];
 				int index = idx[2];
 				
 				if(visited[r][c])
 					continue;
 				
 				count++;
				visited[r][c] = true;
 				
 				for(int d = 0; d < 4; d++) {
 					int nr = r+dr[d];
 					int nc = c+dc[d];
 					
 					if(check(nr,nc))
 						continue;
 					
 					if(map[nr][nc] == 0) {
 						isAble[index] = false;
 						continue;
 					}
 					
 					else if(map[nr][nc] == index && !visited[nr][nc]) {
 						q2.add(new int[] {nr,nc,map[nr][nc]});
 					}		
 				}		
 			}			
			
 			for(int i = 0; i < 2; i++) {
 				int n = tmp[i];
 				int r = li.get(n)[0];
 				int c = li.get(n)[1];
 				map[r][c] = 0;
 			}
 			
 			int sum = 0;
 			
 			for(int i = 3; i < newIndex; i++) {
 				if(isAble[i]) {
 					sum += hashMap.get(i);
 				}
 			}
 			
 			answer = Math.max(answer, sum);
 			
 			return;
 			
 		}
 		
 		for(int i = start; i < size; i++) {
 			tmp[cnt] = i;
 			baduk(i+1, cnt+1);
 		}
 	}
 	
 	static boolean check(int r,int c) {
 		return r>=N || r<0 || c>=M || c<0;
 	}
}
