import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {

	static int N;
	static class Node {
		char t;
		int a, p;
		
		Node(char t, int a, int p){
			this.t = t;
			this.a = a;
			this.p = p;
		}
	}
	static List<Integer>[] li;
	static Node[] map;
	static boolean[] visited;
	static long answer, tmp;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     N = Integer.parseInt(st.nextToken());
     
     li = new List[N+1];
     map = new Node[N+1];
     visited = new boolean[N+1];
     
     for(int i = 1; i <= N; i++) {
    	 li[i] = new ArrayList<>();
     }
     
     for(int i = 2; i <= N; i++) {
    	 st = new StringTokenizer(br.readLine());
    	 char[] t = st.nextToken().toCharArray();
    	 int a = Integer.parseInt(st.nextToken());
    	 int p = Integer.parseInt(st.nextToken());
    	 li[p].add(i);
    	 map[i] = new Node(t[0],a,p);
     }
     
     System.out.println(dfs(1));

 }
 
 static long dfs(int idx) {
	long answer = 0;
	
	for(int i : li[idx]) {
		answer += dfs(i);
	}
	
	if(idx == 1)
		return answer;
	if(map[idx].t == 'S') {
		answer += map[idx].a;
	} else {
		answer = Math.max(0, answer- map[idx].a);
	}
	
	return answer;
 }
 
}

