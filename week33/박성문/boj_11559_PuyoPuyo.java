import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//The main method must be in a class named "Main".
class Main {

	static final int N = 12;
	static final int M = 6;
	static char[][] map;
	static boolean[][] visited;
	static Queue<int[]> q;
	static List<int[]> li;
	static final int[] dr = {1,-1,0,0};
	static final int[] dc = {0,0,1,-1};
	static List<int[]> tmpLi;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     map = new char[N][M];
     
     for(int i = 0; i < N; i++) {
    	map[i] = br.readLine().toCharArray();
     }
     
     int answer = 0;
     q = new LinkedList<>();
     li = new ArrayList<>();	
     tmpLi = new ArrayList<>();
     
     while(true) {
    	 boolean end = true;
    	 
    	 visited = new boolean[N][M];
 
    	 
    	 
    	 for(int i = 0; i < N; i++) {
    		 for(int j = 0; j < M; j++) {
    			 if(visited[i][j])
    				 continue;	 
    			 
    			 if(map[i][j] != '.') {
    				 tmpLi.clear();
    				 q.add(new int[] {i,j});
    				 tmpLi.add(new int[] {i,j});
    				 visited[i][j] = true;
    				 
    				 while(!q.isEmpty()) {
    					 int[] idx = q.poll();
    					 int r = idx[0];
    					 int c = idx[1];
    					 
    					 for(int d = 0; d< 4; d++) {
    						 int nr = r + dr[d];
    						 int nc = c + dc[d];
    						 
    						 if(check(nr,nc) || visited[nr][nc])
    							 continue;
    						 
    						 if(map[i][j] == map[nr][nc]) {
    							
    							 tmpLi.add(new int[] {nr,nc});
    							 visited[nr][nc] = true;
    							 q.add(new int[] {nr,nc});
    						 }
    						 
    					 }
    					 
    				 }
    				 
    				 if(tmpLi.size() >= 4) {

    					 for(int[] idx: tmpLi) {
    						 li.add(idx);
    					 }
    				 }
    				 
    			 }
    			 
    			 
    		 }
    	 }
    	 
    	 int size = li.size();
    	 
    	 if(size < 4) {
    		 System.out.println(answer);
    		 return;
    	 }
    	 
    	for(int i = 0; i < size;i++) {
    		int[] idx = li.get(i);
    		int r = idx[0];
    		int c = idx[1];
    		map[r][c] = '.';
    	}
    	 
    	 
    	
    	for(int i = N-1; i >= 0; i--) {
    		for(int j = 0; j < M; j++) {
    			
    			if(map[i][j] != '.') {
    				int r = i;
    				while(true) {
    					
    					if(check(r+1, j) || map[r+1][j]!='.') {
    						
    						map[r][j] = map[i][j];
    						
    						if(r != i) {
    							map[i][j] ='.';
    						}
    						
    						break;
    					}
    					
    				
    					r++;
    				}
    			
    			}
    			
    		}
    	}
    	
    	 
    	 answer++;
    	 li.clear();
     }
     
     
 }
 
 static boolean check(int r, int c) {
	 return r>=N || r <0 || c>=M || c< 0;
 }
 
}
