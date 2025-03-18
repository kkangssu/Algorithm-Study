import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K,M, T;
    static int[] p,r;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int t= 1; t <= T ; t++){
            sb.append("Scenario ").append(t).append(":").append("\n");
            
            N = Integer.parseInt(br.readLine());
            K = Integer.parseInt(br.readLine());
            
            p = new int[N];
            r = new int[N];
            for(int i =0; i < N ; i++){
                p[i] = i;
                r[i] = 1;
            }
            for(int i =0 ; i < K; i++){
                st= new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a,b);    
            }

            M = Integer.parseInt(br.readLine());
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(find(a) ==find(b)){
                    sb.append(1);
                }else{
                    sb.append(0);
                }
                sb.append("\n");
            }
            sb.append("\n");
            
        }
        System.out.println(sb.toString());
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
