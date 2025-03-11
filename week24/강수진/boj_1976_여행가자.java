import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    
    static int n, m;
    static int[] parent, edge; 
    
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                if(st.nextToken().equals("1")) {
					union(i, j);
				}
            }
        }
        edge = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0;  i < m; i++){
            edge[i] = Integer.parseInt(st.nextToken());
        }
        
        boolean result = true;
        int arrive = find(edge[0]);
        
        for(int i = 1; i < m; i++){
            if(find(edge[i]) != arrive) {
                result = false;
                break;
            }
        }
        
        System.out.println(result ? "YES" : "NO");
    }
    
    static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
    
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y) parent[x] = y;
	}
}
