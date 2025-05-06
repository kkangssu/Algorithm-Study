import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N;
	static int[][] map;
	static Queue<Integer> q;
	static int[][] liked; 
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};

	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
    
     map = new int[N][N];
     int size = N*N;
     liked = new int[N*N+1][4];
     q = new LinkedList<>();
     for(int i = 0; i < size; i++) {
    	 st =new StringTokenizer(br.readLine());
    	 int x = Integer.parseInt(st.nextToken());
    	 q.add(x);
    	 for(int j = 0; j < 4; j++) {
    		 liked[x][j] = Integer.parseInt(st.nextToken());
    	 }   	 
     }
     

     while(!q.isEmpty()) {
    	 int num = q.poll(); 
    	 int l = -1;
    	 int e = -1;
    	 int r = -1;
    	 int c = -1;
    	 
    	 for(int i = 0 ; i < N; i++) {
    		 for(int j = 0; j < N; j++) {
        		 int tmpE = 0;
        		 int tmpL = 0;	 	 
        		 
        		 if(map[i][j]!=0)
        			 continue;
        		 
    			 for(int d = 0; d < 4; d++) {
    				 int nr = i + dr[d];
    				 int nc = j + dc[d];
    				 
    				 if(check(nr,nc))
    					 continue;
    				 
        			 if(map[nr][nc] == 0) {
        				 tmpE++;
        				 continue;
        			 }
        			 
        			 for(int k = 0; k < 4; k++) {
        				 if(map[nr][nc] == liked[num][k]) {
        					 tmpL++;
        				 }
        			 }
    			 }
    			 
    			if(tmpL > l) {
    				l = tmpL;
    				e = tmpE;
    				r = i;
    				c = j;
    				continue;
    				
    			} else if(tmpL == l) {
    				if(tmpE > e) {
        				l = tmpL;
        				e = tmpE;
        				r = i;
        				c = j;
        				continue;
    				}
    				else if(tmpE== e) {
    					if(r > i) {
            				l = tmpL;
            				e = tmpE;
            				r = i;
            				c = j;
            				continue;
    					}	
        				else if(r==i) {
        					if(c > j) {
        	    				l = tmpL;
        	    				e = tmpE;
        	    				r = i;
        	    				c = j;
        	    				continue;
        					}
        				}
    				}
    			}
    			

    			 
    		 }
    	 }
    	 

    	 map[r][c] = num;
     }
     
     int answer = 0;
     
     for(int i = 0; i < N; i++) {
    	 for(int j = 0; j < N; j++) {
    		 
    		 int num = map[i][j];
    		 int cnt = 0;
    		 for(int d = 0; d < 4; d++) {
    			 int nr = i+dr[d];
    			 int nc = j+dc[d];
    			 
    			 if(check(nr,nc))
    				 continue;
    			 
    			 for(int l = 0; l < 4; l++) {
    				 if(map[nr][nc] == liked[num][l]) {
    					 cnt++;
    				 }
    			 }
    			 
    		 }
    		 if(cnt==1) {
    			 answer++;
    		 }else if(cnt==2) {
    			 answer+=10;
    		 } else if(cnt==3) {
    			 answer+=100;
    		 } else if(cnt==4) {
    			 answer+=1000;
    		 }
    		 
    	 }
     }
     System.out.println(answer);
     
     
 }
 static boolean check(int r, int c) {
	 return r>=N|| r < 0 || c>= N || c < 0;
 }

}
