import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static int answer;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
		for(int i = 1; i <= N/2; i++) {
		
			visited = new boolean[N];
			check(0,0,i);
            if(answer == 0){
                System.out.println(answer);
                return;
            }
		}
		
		System.out.println(answer);
		
	}
	
	static void check(int idx, int n, int cnt) {
        if(answer == 0){
            return;
        }
		if(n == cnt) {
			int tmp1 = 0;
			int tmp2= 0;
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
					if(visited[i]  && visited[j]) {
						tmp1 += map[i][j];
						tmp1 += map[j][i];
					}
					
					if(!visited[i] && !visited[j]) {
						tmp2 +=map[i][j];
						tmp2 += map[j][i];
					}
				}
			}

			answer = Math.min(answer, Math.abs(tmp1-tmp2));
			return;
			
		}

		
		for(int i = idx; i < N; i++) {
			visited[i] = true;
			check(i+1, n+1, cnt);
			visited[i] = false;
		}
		
	}
	

}
