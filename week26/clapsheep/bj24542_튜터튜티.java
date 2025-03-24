import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] p,r;
    static int dived = 1000000007;
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p = new int[N+1];
        r = new int[N+1];
        for(int i =1; i < N+1 ; i++){
            p[i] = i;
            r[i] = 1;
        }
        
        for(int i =0 ; i < M; i++){
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);    
        }

        long res = 1;
        for(int i = 1; i <= N; i++) {
            if(p[i] == i) {
                res = (res * r[i]) % dived;
            }
        }
        System.out.println(res);
        
    }
    static void union (int a, int b){
        a = find(a);
        b = find(b);
        if(r[a] > r[b]){
            r[a] += r[b];
            p[b] = a;
        }else{
            r[b] += r[a];
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
