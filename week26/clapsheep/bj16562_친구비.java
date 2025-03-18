import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, K;
    static int[] p,r, w;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        w = new int[N+1];
        p = new int[N+1];
        r = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1 ; i++ ) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N+1 ; i++){
            p[i] = i;
            r[i] = 1;
        }
        for (int i = 0; i < M ; i++ ) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }
        int sum = 0;
        boolean[] vis = new boolean[N+1];
        
        for (int i = 1; i < N+1 ; i++) {
            if(vis[find(i)])continue;
            if(p[i] == i ){
                sum += w[i];
            }
        }
        System.out.println(sum <= K ? sum : "Oh no");
        
    }
    static void union (int a, int b){
        a = find(a);
        b = find(b);
        if(w[a] < w[b]){
            
            p[b] = a;
        }else{
            
            p[a] = b;
        }
    }
    static int find(int v){
        if(p[v] == v){
            return v;
        }else{
            return p[v] = find(p[v]);
        }
    }
}
