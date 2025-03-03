import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	static int N, M, K;
	static int[][] map;
	static int r, c;
	
	static int[][] end;
	
	static Queue<int[]> q;
	static PriorityQueue<int[]> pq;
	static boolean[][] visited;
	static int answer;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j <N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        

        end = new int[M+2][2];
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int sr = Integer.parseInt(st.nextToken())-1;
        	int sc = Integer.parseInt(st.nextToken())-1;
        	int er = Integer.parseInt(st.nextToken())-1;
        	int ec = Integer.parseInt(st.nextToken())-1;
        	
        	map[sr][sc] = i+2;
        	end[i+2][0] = er;
        	end[i+2][1] = ec;
        	
        }
        
        q = new LinkedList<>();
        q.add(new int[] {r,c});
        
        pq = new PriorityQueue<>((o1,o2) -> {
        	if(o1[0] != o2[0])
        		return o1[0]-o2[0];
        	return o1[1]-o2[1];
        });
        
        int count = 0;
        
        while(true) {
        	//System.out.println(r + " " + c + " " + K);
        	if(count == M) {
        		break;
        	}
        	
        	
        	int cnt = 0;
        	visited = new boolean[N][N];
        	
        	while(!q.isEmpty()) {
        		
        		if(cnt > K) {
        			System.out.println(-1);
        			return;
        		}
        		
        		int size = q.size();
        		
        		for(int i = 0; i < size; i++) {
        			int[] idx = q.poll();
        			
        			if(map[idx[0]][idx[1]] >= 2) {
        				pq.add(idx);
        			}
        				
        			
        			for(int d = 0; d < 4; d++) {
        				int nr = idx[0] + dr[d];
        				int nc = idx[1] + dc[d];
        				
        				if(check(nr,nc) || visited[nr][nc] || map[nr][nc] == 1)
        					continue;
        				
        		
        				visited[nr][nc] = true;
        				q.add(new int[] {nr,nc});
        				
        			}
        		}
        		
        		if(!pq.isEmpty()) {
        			break;
        		}
        		cnt++;
        		
        	}
        	
        	if(pq.isEmpty()) {
        		System.out.println(-1);
        		return;
        	}
        	
        	int[] idx2 = pq.poll();
        	pq.clear();
  			q.clear();
        	
        	r = idx2[0];
        	c = idx2[1];
        	K -= cnt;
        	//System.out.println(r + " " + c + " " + K);
        
        	
        	int er = end[map[r][c]][0];
        	int ec = end[map[r][c]][1];
        	map[r][c] = 0;
        	
        	q.add(new int[] {r,c});
        	
        	cnt = 0;
    		boolean ok = false;
        	visited = new boolean[N][N];
        	
        	
        	loop: while(!q.isEmpty()) {
        		
        		if(cnt > K) {
        			System.out.println(-1);
        			return;
        		}
        		
        		int size = q.size();
	
        		for(int i = 0; i < size; i++) {
        			int[] idx = q.poll();
        			
        			if(idx[0] == er && idx[1] == ec) {
        				ok = true;
        				break loop;
        			}
        				
        			
        			for(int d = 0; d < 4; d++) {
        				int nr = idx[0] + dr[d];
        				int nc = idx[1] + dc[d];
        				
        				if(check(nr,nc) || visited[nr][nc] || map[nr][nc] == 1)
        					continue;
        				
        		
        				visited[nr][nc] = true;
        				q.add(new int[] {nr,nc});
        				
        			}
        		}
        		
        		cnt++;
        		
        	}
        	
        	if(!ok) {
        		System.out.println(-1);
        		return;
        	}
        	
        	r = er;
        	c = ec;
        	K += cnt;
  			q.clear();
        	q.add(new int[] {r,c});
        	count++;
        	

        }
        System.out.println(K);
    	
    }

    static boolean check(int r, int c) {
    	return r>= N || r < 0 || c>= N || c < 0;
    }
    
	
}


/* 
(로직상 틀렸지만) 우선순위 큐 주의할 점

우선순위 큐는 요소가 추가될 때 그 요소의 순서를 정렬하고, 이후 r과 c 값이 변경되어도 기존의 요소 순서에는 영향을 주지 않음.
        pq = new PriorityQueue<Node>((o1,o2)->{
        	if((Math.abs(o1.sr - r) + Math.abs(o1.sc -c)) 
        			!= (Math.abs(o2.sr -r ) + Math.abs(o2.sc - c)))
        			return (Math.abs(o1.sr - r) + Math.abs(o1.sc -c)) 
        			- (Math.abs(o2.sr -r ) + Math.abs(o2.sc - c));
        	
        	else if(o1.sr != o2.sr)
        		return o1.sr - o2.sr;
        	
        	return o1.sc - o2.sc;
        		
        });

*/
