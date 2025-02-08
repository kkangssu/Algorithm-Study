import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class bj15661_링크와스타트 {
	static int N;
	static int[][] stat;
	static boolean[] team;
	static int res;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stat = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				stat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		team = new boolean[N];
		res = Integer.MAX_VALUE;
		dfs(0,0);
		System.out.println(res);
		
	}
	static void dfs(int cnt, int teamSize) {
		if(res == 0) return;
		if(cnt == N) {
			if(teamSize > N/2)return;
			
			int a = 0;
			int b = 0;
			for (int i = 0; i < team.length; i++) {
				for (int j = i+1; j < team.length; j++) {
					if(team[i] && team[j]) {
						a+= stat[i][j];
						a+= stat[j][i];
					}
					if(!team[i] && !team[j]) {
						b+= stat[i][j];
						b+= stat[j][i];
					}
				}
			}
			
			res = Integer.min(Math.abs(a-b),res);
			
			return ;
		}

		team[cnt] = true;
		dfs(cnt+1, teamSize+1);
		team[cnt] = false;
		dfs(cnt+1, teamSize);
	}

}
