import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N, Q;
	static int[][] map, tmpMap;
	static boolean[][] visited;
	static int size;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int ice, maxSum; 
	static Queue<int[]> q;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	 StringTokenizer st = new StringTokenizer(br.readLine());
	 N = Integer.parseInt(st.nextToken());
	 Q = Integer.parseInt(st.nextToken());
	 
	 size = (int)Math.pow(2, N);
	 
	 map = new int[size][size];
	 
	 for(int i = 0; i < size; i++) {
		 st = new StringTokenizer(br.readLine());
		 for(int j = 0; j < size; j++) {
			 map[i][j] = Integer.parseInt(st.nextToken());;
		 }
	 }
	 
	 st = new StringTokenizer(br.readLine());
	 for(int t = 0; t < Q; t++) {
		 
		 tmpMap = new int[size][size];
		 
		 int L = Integer.parseInt(st.nextToken());
		 int divSize = (int)Math.pow(2, L);
		 
		 for(int l = 0; l < size; l+= divSize) {
			 for(int k = 0; k < size; k+= divSize) {	 
				 for(int i = 0; i < divSize; i++) {
					 for(int j = 0; j < divSize; j++) {
						 tmpMap[l+i][k+j] = map[l+divSize-1-j][k+i];
					 }
				 }	 
			 } 
		 }
		 
		 boolean[][] isMinus = new boolean[size][size];
		 
		 for(int i = 0; i < size; i++) {
			 for(int j = 0; j < size; j++) {
				 int cnt = 0;
				 if(tmpMap[i][j] == 0)
					 continue;
				 
				 for(int d = 0; d< 4; d++) {
					 int nr = i + dr[d];
					 int nc = j + dc[d];
					 
					 if(check(nr,nc) || tmpMap[nr][nc] == 0)
						 continue;
					 
					 cnt++;
				 }
				 
				 if(cnt < 3 && tmpMap[i][j] >= 1) {
					 isMinus[i][j] = true;
				 }
				 
			 }
		 }
		 
		 
		 for(int i = 0; i < size; i++) {
			 for(int j = 0; j < size; j++) {
				 map[i][j] = tmpMap[i][j];
				 if(isMinus[i][j])
					 map[i][j]--;
			 }
		 }		 
	 }
	 
	 
	 visited =new boolean[size][size];
	 q = new LinkedList();
	 
	 ice = 0;
	 maxSum = 0;
	 
	 for(int i = 0; i < size; i++) {
		 for(int j = 0; j < size; j++) {
			 if(visited[i][j] || map[i][j] == 0)
				 continue;
			 
			 int tmpSum = 0;
			 q.add(new int[] {i,j});
			 
			 visited[i][j] = true;
			 
			 while(!q.isEmpty()) {
				 int[] idx = q.poll();
				 int r = idx[0];
				 int c = idx[1];
				 
				 ice+=map[r][c];
				 tmpSum += 1;
				 
				 for(int d = 0; d< 4; d++) {
					 int nr = r+dr[d];
					 int nc = c+dc[d];
					 
					 if(check(nr,nc) || visited[nr][nc] || map[nr][nc] == 0)
						 continue;
					 
					 visited[nr][nc] = true;
					 q.add(new int[] {nr,nc});
				 }
				 
			 }
			 
			 maxSum = Math.max(maxSum, tmpSum);
			 
		 }
	 }
	 
	 System.out.println(ice);
	 System.out.println(maxSum);
	 
	 	
 }
 
 static boolean check(int r, int c) {
	 return r>= size || r < 0 || c >= size || c < 0;
 }

}
