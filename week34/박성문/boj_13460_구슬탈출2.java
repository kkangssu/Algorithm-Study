import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {

	static int N, M;
	static char[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static class Bead {
		int bx,by,rx,ry;
		
		Bead(int bx, int by, int rx, int ry){
			this.bx = bx;
			this.by = by;
			this.rx = rx;
			this.ry = ry;
		}
	}
	static int rx,ry,bx,by;
	static Queue<Bead> q; 
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     M = Integer.parseInt(st.nextToken());
     
     bx = -1;
     by = -1;
     rx = -1;
     ry = -1;
     
     map = new char[N][M];
     
     for(int i = 0; i < N; i++) {
    	 map[i] = br.readLine().toCharArray();
    	 for(int j = 0; j < M; j++) {
    		 if(map[i][j] == 'R') {
    			 rx = i;
    			 ry = j;
    		 } else if (map[i][j] == 'B') {
    		
    			 bx = i;
    			 by = j;
    		 }
    	 }
     }

     int answer = 0;
     q = new LinkedList<>();
     
     q.add(new Bead(bx,by,rx,ry));
     
     for(int i = 0; i <= 10; i++) {
    	 
    	 int size = q.size();
    	 
    	 for(int j = 0; j < size; j++) {
    		 Bead bead = q.poll();

        		 bx = bead.bx;
        		 by = bead.by;
        		 rx = bead.rx;
        		 ry = bead.ry;
        		 
        		 
        		 if(map[rx][ry] == 'O' && map[bx][by] != 'O') {
        			 System.out.println(answer);
        			 return;
        		 }
        		 
        		 
        		 if(rx > bx) {
            		 go(0, true);
            		 go(1, false); 
        		 } else {
        			go(0, false);
        			go(1, true);
        		 }
        		 
        		 if(ry > by) {
        			 go(2, false);
        			 go(3, true);
        		 } else {
        			 go(2, true);
        			 go(3, false);
        		 }
    	 }
    	 
    	 answer++;
    	 
     }
     
     System.out.println(-1);
     
 }

 static boolean check(int r, int c) {
	 return r>=N || r< 0 || c>=M || c < 0;
 }
 static void go(int d, boolean rFirst) {
	 int rdx = rx;
	 int rdy = ry;
	 int bdx = bx;
	 int bdy = by;
	 
	 
	 if(rFirst) {
		 
		 while(true) {
			 rdx += dr[d];
			 rdy += dc[d];
			 
			 if(check(rdx,rdy) || map[rdx][rdy] == '#'|| (bdx==rdx && bdy == rdy)) {
				 rdx -= dr[d];
				 rdy -= dc[d];
				 break;
			 }
			 if(map[rdx][rdy] == 'O')
				 break;
			 
		 }
		 
		 while(true) {
			 bdx += dr[d];
			 bdy += dc[d];
			 
			 if(check(bdx,bdy) || map[bdx][bdy] =='#' || (bdx==rdx && bdy == rdy && map[rdx][rdy] !='O' )) {
				 bdx -= dr[d];
				 bdy -= dc[d];
				 break;
			 }
			 if(map[bdx][bdy] == 'O')
				 break;
		 }
		 
		 
	 } else {
		 while(true) {
			 bdx += dr[d];
			 bdy += dc[d];
			 
			 if(check(bdx,bdy) || map[bdx][bdy] == '#' || (bdx==rdx && bdy == rdy)) {
				 bdx -= dr[d];
				 bdy -= dc[d];
				 break;
			 }
			 if(map[bdx][bdy] == 'O')
				 break;
		 }
		 
		 while(true) {
			 
			 rdx += dr[d];
			 rdy += dc[d];

			 if(check(rdx,rdy) || map[rdx][rdy] == '#'|| (bdx==rdx && bdy == rdy)) {
				 rdx -= dr[d];
				 rdy -= dc[d];
				 break;
			 }
			 if(map[rdx][rdy] == 'O')
				 break; 
		 }
		 
		 
	 }

	 if(map[bdx][bdy] != 'O') {
		 q.add(new Bead(bdx,bdy, rdx,rdy));
	 }
	 
	 return;
	 
	 
 }
 
}
