import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {

	static int N, M, K;
	static int[][] map;
	static List<int[]> fireBall;
	static List<int[]> tmpFireBall;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int[] tmp;
	static boolean[] visited;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     M = Integer.parseInt(st.nextToken());
     K = Integer.parseInt(st.nextToken());
     

     fireBall = new ArrayList<>();
     tmpFireBall = new ArrayList<>();
     
     for(int i = 0; i < M; i++) {
    	 st = new StringTokenizer(br.readLine());
    	 tmp = new int[5];
    	 for(int j = 0; j < 5; j++) {
    		 tmp[j] = Integer.parseInt(st.nextToken());
    	 }
    	 fireBall.add(tmp);
     }
     // r,c, 질량, 방향, 속력
     for(int k = 0; k < K; k++) {
    	 
    	 int size = fireBall.size();
         map = new int[N][N];
         
    	 for(int i = 0; i < size; i++) {
    		 int[] idx = fireBall.get(i);
    		 int r = idx[0];
    		 int c = idx[1];
    		 int m = idx[2];
    		 int s = idx[3];
    		 int d = idx[4];

    		 for(int j = 0; j < s; j++) {
    			 r += dr[d];
    			 c += dc[d];
    		 }
    		 
    		 if(r >= N) {
    			 r%=N;
    		 }
    		 if(c >= N) {
    			 c%=N;
    		 }
    		 if(r < 0) {
    			 while(r < 0) {
    				 r += N;
    			 }
    		 }
    		 if(c < 0) {
    			 while(c < 0) {
    				 c += N;
    			 }
    		 }
    		 
    		 
    		 map[r][c] += 1;
    		 
    		 tmpFireBall.add(new int[] {r,c,m,s,d});
    		
    		 
    	 }
    	 
    	 fireBall.clear();
    	 
    	 size = tmpFireBall.size();
    	 visited=  new boolean[size];
    	 for(int i = 0; i < size ; i++) {
    		 if(visited[i])
    			 continue;
    		 
    		 int[] idx = tmpFireBall.get(i);
    		 int r = idx[0];
    		 int c = idx[1];
    		 int m = idx[2];
    		 int s = idx[3];
    		 int d = idx[4];
    		
    		 visited[i] = true;
    		 
    		 if(map[r][c] == 1) {
    			 fireBall.add(idx);
    			 continue;
    		 }
    		 
    		 int mSum = m;
    		 int sSum = s;
    		 boolean isDSame = true;
    		 int dDiv = d%2;
    		 int cnt = 1;
    		 
    		 for(int j = 0; j < size; j++) {
    			 if(i == j)
    				 continue;
    			 
        		 int[] tmpIdx = tmpFireBall.get(j);
        		 int tmpR = tmpIdx[0];
        		 int tmpC = tmpIdx[1];
        		 int tmpM = tmpIdx[2];
        		 int tmpS = tmpIdx[3];
        		 int tmpD = tmpIdx[4];
        		 
        		 if(tmpR == r && tmpC==c) {
        		
        			 visited[j] = true;
        			 cnt++;
        			 mSum += tmpM;
        			 sSum += tmpS;
        			 
        			 if(dDiv !=tmpD%2) {
        				 isDSame =false;
        			 }
        		 }
    			 
    		 }
    		 

    		 int mDiv = mSum/5;
    		 int sDiv = sSum/cnt;
    		 
    		 if(mDiv == 0) {
    			 continue;
    		 }
    		 
    		 if(isDSame) {
    			 for(int j = 0; j < 8; j+=2) {
    				 fireBall.add(new int[] {r,c,mDiv,sDiv,j});
    			 }
    		 } else {
    			 for(int j = 1; j < 8; j+=2) {
    				 fireBall.add(new int[] {r,c,mDiv,sDiv,j});
    			 } 
    		 }
    		 
    	 }
    	 tmpFireBall.clear();
    	 
     }
     int answer = 0;
     
     for(int[] idx : fireBall) {
    	 answer += idx[2];
     }
     
     System.out.println(answer);
     
 }

}
