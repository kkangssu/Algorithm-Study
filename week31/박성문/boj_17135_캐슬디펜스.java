import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N, M, D;
	static int[] tmp;
	static int answer;
	static List<int[]> li;
	static int[][] attack;
	static Queue<int[]> q;
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     M = Integer.parseInt(st.nextToken());
     D = Integer.parseInt(st.nextToken());
     
     li = new ArrayList<>();
  
     for(int i = 0; i < N; i++) {
    	 st = new StringTokenizer(br.readLine());
    	 for(int j = 0; j < M; j++) {
    		 int x = Integer.parseInt(st.nextToken());
    		 if(x == 1) {
    			 li.add(new int[] {i,j});
    		 }
    	 }
     }

     answer = 0;
     tmp = new int[3];
     comb(0,0);
     
     System.out.println(answer);
     
 }
 
 static void comb(int cnt, int start) {
	 if(cnt ==3 ) {	 
		 game();		 
		 return;
	 }
	 
	 for(int i = start; i < M; i++) {
		 tmp[cnt] = i;
		 comb(cnt+1, i+1);
	 }
 }
 
 static void game() {
	 int total = li.size();
	 int cnt = 0;
	 List<int[]> li2 = new ArrayList<>();
	 for(int[] i: li) {
		 li2.add(i);
	 }

	 while(total!=0) {
		boolean[][] map = new boolean[N][M];
		int[][] attack = new int[3][3];
		for(int i = 0; i < 3;i++) {
			attack[i][0] = -1;
		}
		
		q = new LinkedList<>();
		
		for(int[] i : li2) {
			
			int r = i[0];
			int c = i[1];
			
			for(int j = 0; j < 3; j++) {
				int dist = Math.abs(N-r) + Math.abs(tmp[j]-c);
				if(dist <= D) {
					if(attack[j][0]==-1) {
						attack[j][0] = r;
						attack[j][1] = c;
						attack[j][2] = dist;
						continue;
					} else {
						int nowDist =  attack[j][2];
						if(dist < nowDist) {
							attack[j][0] = r;
							attack[j][1] = c;
							attack[j][2] = dist;
							continue;
						} else if(dist==nowDist) {
							if(c < attack[j][1]) {
								attack[j][0] = r;
								attack[j][1] = c;
								attack[j][2] = dist;
								continue;
							}
						}
					}
				}
			}

		}
		
		for(int i =0; i  < 3;i++) {
			int r = attack[i][0];
			int c = attack[i][1];
			if(r==-1)
				continue;
			map[r][c] = true;
		}

		
		for(int[] i: li2) {
			int r = i[0];
			int c = i[1];
			
			if(map[r][c]) {
				cnt++;
				continue;
			}
			if(r+1 >= N)
				continue;
			
			q.add(new int[] {r+1,c});
		}

		total = li2.size();
		li2.clear();
		while(!q.isEmpty()) {
			li2.add(q.poll());
		}
	
	 }
	answer = Math.max(answer, cnt);
	return;		 
 }
}
