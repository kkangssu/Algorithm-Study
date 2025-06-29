import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N, M, L;
	static int[] gun;
	static int[][] map;
	static int answer;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	 StringTokenizer st = new StringTokenizer(br.readLine());
	 M = Integer.parseInt(st.nextToken());
	 N = Integer.parseInt(st.nextToken());
	 L = Integer.parseInt(st.nextToken());
	 
	 gun = new int[M];
	 st = new StringTokenizer(br.readLine());
	 
	 for(int i = 0; i < M; i++) {
		 gun[i] = Integer.parseInt(st.nextToken());
	 }
	 
	 map = new int[N][2];
	 for(int i = 0; i < N; i++) {
		 st = new StringTokenizer(br.readLine());
		 map[i][0] = Integer.parseInt(st.nextToken());
		 map[i][1] = Integer.parseInt(st.nextToken());
	 }
	 
	 answer = 0;
	 
	 Arrays.sort(gun);

	 for(int i = 0; i < N; i++) {
		 int left = 0;
		 int right = M;
		 
		 while(left <= right) {
			 int mid = (left +right)/2;
			 int a = map[i][0];
			 int b = map[i][1];
			 int x = gun[mid];
			 if(Math.abs(x - a) + b <= L) {
				 answer++;
				 break;
			 }
			 
			 if(a < x) {
				 right = mid-1;
			 } else {
				 left = mid+1;
			 }		 
		 }
	 }
	 System.out.println(answer); 
 }

}
