import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static boolean[][] nemmo;
    static int result, n, m;
    // static int[] dr = { -1, -1, 0};
    // static int[] dc = {-1, 0, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nemmo = new boolean[n][m];

        bt(0);

        System.out.println(result);
    }

    static void bt(int idx){
        if(idx == n*m){
            result++;
            return;
        }

        int r = idx/m;
        int c = idx%m;

        if(r > 0 && c > 0 &&nemmo[r-1][c] && nemmo[r-1][c-1] && nemmo[r][c-1]){
            bt(idx+1);    // 이번칸은 0이 들어가야함
        }
        else{
            nemmo[r][c] = true;
            bt(idx+1);
            nemmo[r][c] = false;
            bt(idx+1);
        }
    }
}
