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
		dfs(0);
		System.out.println(res);
		
	}
	static void dfs(int cnt) {
		if(res == 0) return;
		if(cnt == N) {

			List<Integer> teamA = new ArrayList<>();
			List<Integer> teamB = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if(team[i]) {
					teamA.add(i);
				}else {
					teamB.add(i);
				}
			}
			if(teamA.size()==0 || teamB.size()==0)return;
			int a = 0;
			int b = 0;
			for (int i = 0; i < teamA.size(); i++) {
				for (int j = i+1; j < teamA.size(); j++) {
					if(i==j)continue;
					a+= stat[teamA.get(i)][teamA.get(j)];
					a+= stat[teamA.get(j)][teamA.get(i)];
				}
			}
			for (int i = 0; i < teamB.size(); i++) {
				for (int j = i+1; j < teamB.size(); j++) {
					if(i==j)continue;
					b+= stat[teamB.get(i)][teamB.get(j)];
					b+= stat[teamB.get(j)][teamB.get(i)];
				}
			}
			res = Integer.min(Math.abs(a-b),res);
			
			return ;
		}

		team[cnt] = true;
		dfs(cnt+1);
		team[cnt] = false;
		dfs(cnt+1);
	}

}
