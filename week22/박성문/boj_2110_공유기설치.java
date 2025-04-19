import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, C;
	static int[] map;
	static int left, right, mid;

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[N];
		
		for(int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(map);

			
			left = 1;
			right = map[N-1]-map[0];
			
			while(left <= right) {
				mid = (left +right)/2;
				int cnt = 1;
				int tmp = map[0];
				for(int i = 1; i< N; i++) {
					if(map[i] -tmp>= mid) {
						cnt++;
						tmp = map[i];
					}
				}
				
				if(cnt >= C) {
					left = mid+1;
				} else {
					right = mid-1;
				}
				
			}
			
			System.out.println(left-1);
		
		
		
	}
	
}
