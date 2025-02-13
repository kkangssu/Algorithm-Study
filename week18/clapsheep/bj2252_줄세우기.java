import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2252_줄세우기 {
	static int N, M;
	static int[] degree; // 각 인덱스 = 사람의 진입차수
	static List<Integer>[] adj;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}
		degree = new int[N+1];
		
		
		for (int m = 0; m < M; m++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			degree[b]++;
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < N+1; i++) {
			if(degree[i]==0) {
				q.offer(i);
			}
		}
		StringBuilder sb= new StringBuilder();
		while(!q.isEmpty()) {			
			int cur = q.poll();
			sb.append(cur).append(" ");
			for (int i : adj[cur]) {
				degree[i]--;
				if(degree[i]== 0) {
					q.offer(i);
				}
			}
		}
		System.out.println(sb.toString());
	}

}
