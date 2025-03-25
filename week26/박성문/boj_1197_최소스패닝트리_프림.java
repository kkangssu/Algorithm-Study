import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// The main method must be in a class named "Main".
public class Main {
    
	static int N, E;
	static List<int[]>[] li;
	static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
       
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        li = new List[N+1];
        for(int i = 1; i <= N; i++) {
        	li[i] =new ArrayList<>();
        }
        
        visited = new boolean[N+1];
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	
        	li[s].add(new int[] {s,e,w});
        	li[e].add(new int[] {e,s,w});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2)->{
        	return o1[2] - o2[2];
        });
        int cnt = 1;
        int answer = 0;
        pq.addAll(li[1]);
        visited[1] = true;
        while(cnt != N) {
        	int[] idx = pq.poll();
        	if(visited[idx[1]])
        		continue;
        	
        	answer += idx[2];
        	visited[idx[1]] = true;
        	cnt++;
        	pq.addAll(li[idx[1]]);
        	
        }
        System.out.println(answer);
        
        
    }
}
