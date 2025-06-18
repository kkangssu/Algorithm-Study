import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {

	static int N, M;
	static boolean[] map;
	static int[][] dp;
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     M = Integer.parseInt(st.nextToken());
     
     map = new boolean[N+1];
     
     st = new StringTokenizer(br.readLine());
     for(int i = 1; i <= M; i++) {
    	 map[Integer.parseInt(st.nextToken())] = true;
     }
     
     int answer = 0;
     
     List<Integer> li = new LinkedList<>();
     
     
     for(int i = 1; i <= N; i++) {
    	 if(!map[i]) {
    		 li.add(i);
    	 }
     }
     int size = li.size();
     
     if(size == 0) {
         System.out.println(0);
         return;
     }
     
     int start = li.get(0);
     int tmp = 7;
     
     for(int i = 1; i < size; i++) {
    	 
    	 int range = li.get(i) - start;
    	 
    	 
    	 if(5 + (range+1) *2 <= tmp + 7) {
    		 tmp = 5 + (range+1)*2;
    	 }	 
    	 else {
    		 answer += tmp;
    		 tmp = 7;
    		 start = li.get(i);
    	 }
    	 
     }
     
     answer += tmp;
     
     System.out.println(answer);

   
 }
 
}
