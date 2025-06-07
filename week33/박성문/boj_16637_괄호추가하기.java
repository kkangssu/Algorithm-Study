import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {

	static int N;
	static int[] num;
	static char[] sign;
	static List<Integer> numLi;
	static List<Character> signLi;
	static int answer;
	static boolean[] visited;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     char[] word = br.readLine().toCharArray();
     if(N==1) {
    	 System.out.println(word[0]);
    	 return;
     }
     num = new int[N/2+1];
     sign = new char[N/2];
     int idx = 0;
     for(int i = 0; i < N; i+=2) {
    	 num[idx++] = word[i] -'0';
     }
     idx = 0;
     for(int i = 1; i < N; i+=2) {
    	 sign[idx++] = word[i];
     }
     
     visited = new boolean[N/2];
     numLi = new ArrayList<>();
     signLi = new ArrayList<>();
     answer =Integer.MIN_VALUE;
     subset(0);
     
     System.out.println(answer);
 }
 
 
 static void subset(int cnt) {
	 if(cnt==N/2) {
		numLi.clear();
		signLi.clear();
		boolean[] isCal = new boolean[N/2+1];
		
		 for(int i = 0; i < N/2; i++) {
			 if(!visited[i]) {
				 if(i>0 && isCal[i-1]) {
					 signLi.add(sign[i]);
					 continue;
				 }			 
				 numLi.add(num[i]);
				 signLi.add(sign[i]);
				 
			 } else {
				 if(i>0 && isCal[i-1]) {
					 signLi.add(sign[i]);
					 continue;
				 }
				 else {
					 numLi.add(cal(num[i], num[i+1], sign[i]));
					 isCal[i] = true;
				 }	 
			 }
		 }
		 
		 if(!isCal[N/2-1]) {
			 numLi.add(num[N/2]);
		 }
		 
		 int size = numLi.size();
		 int tmp = numLi.get(0);
		 

		 
		 for(int i = 1; i < size; i++) {
			 tmp = cal(tmp, numLi.get(i), signLi.get(i-1));
		 }
		 
		 
		 answer= Math.max(answer, tmp);
		 return;
	 }
	 
	 
	 visited[cnt] = true;
	 subset(cnt+1);
	 visited[cnt] = false;
	 subset(cnt+1);
	 
 }

 
 static int cal(int n1, int n2, char s) {
	 if(s=='+')
		 return n1+n2;
	 if(s=='-')
		 return n1-n2;
	 return n1*n2;
 }
 
}
