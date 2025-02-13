package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L;
	static int[] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		map = new int[N+2];
		for(int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		map[0] = 0;
		map[N+1] = L;

		int left = 1;
		int right = L-1;
		int answer = 0;
		
		Arrays.sort(map);
		
		while(left <= right) {
			int mid = (left + right)/2;
			//System.out.println(left + " " + right + " " + mid);
			int cnt = 0;
			
			for(int i = 0; i < N+1; i++) {
				int tmp = (map[i+1] - map[i]-1)/mid;
	
				//System.out.println(cnt);
				cnt+=tmp;
			}
			//System.out.println(cnt);
			if(cnt > M) {
				left = mid+1;
			} else {
				right = mid-1;
				answer = mid;
			}
			

		}
		
		System.out.println(answer);
		
		
	}
	
	
}
