package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// The main method must be in a class named "Main".
public class Main2 {
    
	static int V,E;
	static int[] p;
	static PriorityQueue<int[]> pq;
	static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        p = new int[V+1];
        
        for(int i = 1; i <= V; i++) {
        	p[i] = i;
        }
        
        pq = new PriorityQueue<>((o1,o2)->{
        	return o1[2] - o2[2];
        });
        		
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int s =Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	pq.add(new int[] {s,e,w});
  
        }
        answer = 0;
        
        while(!pq.isEmpty()) {
        	int[] idx = pq.poll();
        	int n1 = idx[0];
        	int n2 = idx[1];
        	int w = idx[2];
        	int p1 = find(n1);
        	int p2 = find(n2);
        	if(p1 != p2) {
            	p[p1] = p2;
            	answer += w;
        		continue;
        	}

        	
        }
        System.out.println(answer);
    }	  
    
    static int find(int n) {
    	if(n != p[n])
    		p[n] = find(p[n]);
    	return p[n];
    }
    
}
