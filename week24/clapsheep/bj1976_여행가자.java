import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] p;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        p = new int[N + 1];
        for(int i = 1; i < N+1 ; i++){
            p[i] = i;
        }
        
        for(int i = 1; i < N+1 ; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1 ; j++){
                int cur = Integer.parseInt(st.nextToken());
                if(cur ==1){
                    union(i,j);
                }
            }
        }
        st=  new StringTokenizer(br.readLine());
        int first = find(Integer.parseInt(st.nextToken()));
        for(int i = 1; i < M; i++){
            int cur = Integer.parseInt(st.nextToken());
            if(first != find(cur)){
                System.out.println("NO");
                return;
            }
        }
                System.out.println("YES");
    }
    public static int find (int v){
        if(p[v] != v){
            return p[v] = find(p[v]);
        }else{
            return p[v];
        }
    }
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a > b){
            p[b] = a;
        }else{
            p[a] = b;
        }
    }
}
