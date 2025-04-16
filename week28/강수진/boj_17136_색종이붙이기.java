import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int[][] map;
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int min, n1;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[10][10];
        for(int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) n1++;
            }
        }
        min = Integer.MAX_VALUE;

        bt(0, 0, 0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void bt(int r, int c, int cnt, int num1){
        if(cnt >= min) return;
        
        if(num1 == n1) {
            min = Math.min(min, cnt);
            return;
        }
        
        while(r < 10){
            while(c < 10){
                if(map[r][c] == 1) break;
                c++;
            }
            if(c < 10) break;
            r++;
            c = 0;
        }

        if(r >= 10) return;

        for(int i = 5; i > 0; i--){
            if(paper[i] == 0) continue;
            if(!attach(r, c, i)) continue;
            paper[i]--;
            bt(r, c+1, cnt+1, num1+i*i);
            detach(r, c, i);
            paper[i]++;
        }
    }

    static boolean attach(int r, int c, int s){
        for(int i = r; i < r+s; i++){
            for(int j = c; j < c+s; j++){
                if(i >= 10) return false;
                if(j >= 10) return false;
                if(map[i][j] != 1) return false;
            }
        }
        for(int i = r; i < r+s; i++){
            for(int j = c; j < c+s; j++){
                map[i][j] = 2;
            }
        }
        return true;
    }

    static void detach(int r, int c, int s){
        for(int i = r; i < r+s; i++){
            for(int j = c; j < c+s; j++){
                map[i][j] = 1;
            }
        }
    }
}
