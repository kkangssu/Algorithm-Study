import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N,M;
    static int[] p;
    static HashMap<Integer, Integer> map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p = new int[N+1];
        map = new HashMap<>();

        for(int i = 1; i<= N;i++){
            p[i] =i;
            map.put(i, 1);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            int p1 = find(n1);
            int p2 = find(n2);
            if(p1!=p2){
                p[p2] = p1;
                map.put(p1, map.get(p1) + map.get(p2));
                map.remove(p2);
            }
            
        }

        long answer = 1;

        for(int i : map.keySet()){
            answer = (answer * map.get(i)) % 1000000007;
        }
        System.out.println(answer);

        
    }

    static int find(int n) {
        if(n!=p[n])
            p[n] = find(p[n]);
        return p[n];
    }
}
