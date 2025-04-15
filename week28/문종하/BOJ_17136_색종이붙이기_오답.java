package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    1로 채워두기

* */
public class BOJ_17136_색종이붙이기_오답 {
    static int [] count;
    static boolean[][] visited;
    static int ans = Integer.MAX_VALUE;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = new int[6];
        Arrays.fill(count, 5);
        StringTokenizer st;
        visited = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                visited[i][j] = st.nextToken().charAt(0) == '1';
            }
        }

        dfs(0,0);

        System.out.println(ans==Integer.MAX_VALUE?-1:ans);
    }
    static void dfs(int n, int cnt) {
        if(ans <= cnt) return;
        if(n==100){
            ans = cnt;
            return;
        }

        int i = n / 10;
        int j = n % 10;

        if(!visited[i][j]) dfs(n+1, cnt);

        for (int k = 5; k > 0; k--) {
            if(check(i,j,k)) {
                count[k]--;
                if(count[k]>=0) dfs(n, cnt+1);
                count[k]++;
                set(i,j,k,true);
            }
        }
    }
    static boolean check(int i, int j, int depth){
        if(i+depth >= 10 || j+depth >= 10) return false;
        for(int k=0;k<depth;k++){
            for(int l=0;l<depth;l++){
                if(!visited[i+k][j+l]) return false;
            }
        }
        set(i,j,depth,false);
        return true;
    }
    static void set(int i, int j, int depth, boolean state){
        for(int k=0;k<depth;k++){
            for(int l=0;l<depth;l++){
                visited[i+k][j+l] = state;
            }
        }
    }
}
