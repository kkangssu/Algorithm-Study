import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N;
	static boolean[][] visited;
	static int x,y,d,g;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,-1,0,1};
	static List<Integer> li;
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     visited = new boolean[101][101];
     
     for(int i = 0; i < N; i++) {
    	 st = new StringTokenizer(br.readLine());
    	 x = Integer.parseInt(st.nextToken());
    	 y = Integer.parseInt(st.nextToken());
    	 d = Integer.parseInt(st.nextToken());
    	 g = Integer.parseInt(st.nextToken());

    	 int cnt = 0;
    	 visited[x][y] = true;
    	 li = new ArrayList<>();
		 li.add(d);
    	 int nx = x+dr[d];
    	 int ny = y+dc[d];
    	 visited[nx][ny] = true;
    	 
    	 while(cnt < g) {
    		 int size = li.size();
    		 for(int j = size-1; j >=0 ;j--) {
    			 int nd = (li.get(j)+1)%4;
    			 nx+=dr[nd];
    			 ny+=dc[nd];

    			 visited[nx][ny] = true;
        		 li.add(nd);

    		 }
    		 cnt++;
    	 }
    	 
     }
     int answer = 0;
     for(int i = 0; i < 100; i++) {
    	 for(int j = 0; j < 100; j++) {
    		 if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1])
    			 answer++;
    	 }
     }
     
     System.out.println(answer);
 }

}
