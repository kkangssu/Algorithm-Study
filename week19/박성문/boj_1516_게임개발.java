import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] count;
	static List<Integer>[] li;
	static Queue<Integer> q;
	static int[] map;
	static int[] answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		li = new ArrayList[N+1];
		count = new int[N+1];
		map = new int[N+1];
		q = new LinkedList<>();
		
		
		for(int i = 1; i <= N; i++) {
			li[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= N; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[i] = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				int n = Integer.parseInt(st.nextToken());
				if(n==-1) {
					break;
				}
				count[i]++;
				li[n].add(i);
				
			}
			
			if(count[i] == 0) {
				q.add(i);
			}
			
		}
		answer=  new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			answer[i] = map[i];
		}
		//System.out.println(Arrays.toString(count));
		while(!q.isEmpty()) {
			int size =q.size();
			
			int n = q.poll();	
			
			for(int m : li[n]) {

				count[m]--;
				answer[m] = Math.max(answer[m], answer[n] + map[m]);
				if(count[m] == 0) {
					q.add(m);
				}
				
			}
		}
		
		for(int i = 1; i <= N; i++) {
			System.out.println(answer[i]);
		}

		
	}
