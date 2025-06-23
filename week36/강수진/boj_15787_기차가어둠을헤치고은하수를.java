import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int n, m;
    static int[] train;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        train = new int[n];
        
        int order = 0;
        int a = 0;
        int b = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            order = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken())-1;
            switch (order) {
                case 1:
                    b = Integer.parseInt(st.nextToken())-1;
                    train[a] = train[a] | (1 << b);
                    break;
                case 2:
                    b = Integer.parseInt(st.nextToken())-1;
                    train[a] = train[a] & ~(1 << b);
                    break;
                case 3:
                    train[a] = (train[a] << 1) & ((1 << 20) - 1);
                    break;
                case 4:
                    train[a] >>= 1;
            }
        }

        int result = 0;
        boolean[] visited = new boolean[1 << 20];
        for(int i = 0; i < n; i++){
            if(visited[train[i]]) continue;
            visited[train[i]] = true;
            result++;
        }

        System.out.println(result);
    }
}
