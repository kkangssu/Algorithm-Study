import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M, K;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<int[]> q;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = c[j] -'0';
			}
			
		}
		
		q = new LinkedList<>();
		q.add(new int[] {0,0,0});
		int cnt = 1;
		visited = new boolean[N][M][K+1];
		while(!q.isEmpty()) {
			
			int size = q.size();
			for(int i = 0; i < size; i++) {
				int[] idx = q.poll();
				int r = idx[0];
				int c = idx[1];
				int k = idx[2];
				
				if(r == N-1 && c == M-1) {
					System.out.println(cnt);
					return;
				}
				
				//System.out.println(r + " " + c + " " + k + " " + cnt);
				for(int d =0 ; d< 4; d++) {
					int nr = r + dr[d];
					int nc = c+ dc[d];
					if(check(nr,nc))
						continue;

					
					if(map[nr][nc] == 0 && !visited[nr][nc][k]) {
		
						visited[nr][nc][k] = true;
						q.add(new int[] {nr,nc,k});
					}
					
					if(map[nr][nc] == 1) {
						if(K >= k+1 && !visited[nr][nc][k+1]) {
							visited[nr][nc][k+1] = true;
							q.add(new int[] {nr,nc,k+1});
						}
					}
					
				}
				
			}
			cnt++;
		}
		System.out.println(-1);
		
		
	}
	static boolean check(int r, int c) {
		return r>=N || r<0 || c>=M || c < 0;
	}
}
