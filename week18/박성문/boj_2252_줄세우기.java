package baekjoon_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 줄세우기{
	static int N, M;
	static List<Integer>[] li;
	static Queue<Integer> q;
	static int[] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1];
		
		li = new ArrayList[N+1];
		
		for(int i = 0;i <=N;i++) {
			li[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			li[n1].add(n2);
			map[n2]++;
		}
		q = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(map[i] == 0) {
				q.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!q.isEmpty()) {
			
			int n = q.poll();
			sb.append(n).append(' ');
			
			for(int i : li[n]) {
				map[i]--;
				if(map[i] == 0) {
					q.add(i);
				}
			}
		}
		System.out.println(sb.toString());
		
	}
}
