import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int M;

    static int[] r;
    static int[] p;
    static List<Integer> li;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        r = new int[N+1];
        p = new int[N+1];
        
        for(int i = 1; i <= N; i++){
            r[i] = 1;
            p[i] = i;
        }
     
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
          for(int j = 1; j <= N; j++){
            int n = Integer.parseInt(st.nextToken());
            if(n==1){
        
                int s = find(i);
                int e = find(j);

                if(s!=e){
                    if(p[s] > p[e]){
                        r[s] += r[e];
                        p[e] = s;
                    } else{
                        r[e] += r[s];
                        p[s] = e;
                    }
                }
                
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        li = new ArrayList<>();
        for(int i = 0; i < M; i++){
            li.add(Integer.parseInt(st.nextToken()));
        }
            
        for(int i = 0; i < M-1; i++){
            if(find(li.get(i)) != find(li.get(i+1))) {
                System.out.println("NO");
                return;
            }
        }
        
         System.out.println("YES");
        
    }

    static int find(int n){
        if(n!=p[n])
            p[n] = find(p[n]);
        return p[n];
    }
    
    
}
