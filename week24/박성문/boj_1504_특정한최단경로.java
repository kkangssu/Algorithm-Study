import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

	static int N, E;
	static int[][] map;
	static List<Node>[] li;
	static class Node {
		int s, e, w;
		Node(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	static long answer1;
	static long answer2;
	static long[] visited;
	static ArrayDeque<Node> q;
	 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        li = new List[N+1];
        
        for(int i = 1; i <= N; i++) {
        	li[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	
        	li[s].add(new Node(s,e,w));
        	li[e].add(new Node(e,s,w));
        }
        
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
       
        
        answer1 = 0;
        answer1 += dijk(1, v1);
        answer1 +=dijk(v1,v2);
        answer1 += dijk(v2, N);
        
        answer2 = 0;
        answer2 += dijk(1, v2);
        answer2 +=dijk(v2,v1);
        answer2 += dijk(v1, N);
     
        if(answer1 >= Integer.MAX_VALUE && answer2 >= Integer.MAX_VALUE) {
        	System.out.println(-1);
        } else {
        	System.out.println(Math.min(answer1, answer2));
        }
    }
    
    
    static long dijk(int n1, int n2) {
        q = new ArrayDeque<>();
        visited= new long[N+1];
        
        Arrays.fill(visited, Integer.MAX_VALUE);
        q.addAll(li[n1]);
        visited[n1] = 0;
        
        while(!q.isEmpty()) {
        	Node n = q.poll();
        	
        	if(visited[n.e] > visited[n.s] + n.w) {
        		visited[n.e] = visited[n.s] + n.w;
        		q.addAll(li[n.e]);
        	}
        	
        }
 
        
        return visited[n2];
        
    }
    
}
