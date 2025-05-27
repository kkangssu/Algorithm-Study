import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static final int MOD = 1000007;
    static long[][] memo;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        memo = new long[h][w];
        
        System.out.println((getWay(y, x) * getWay(h - y - 1, w - x - 1)) % MOD);
    }

    static long getWay(int y, int x) {
        if(y == 0 || x == 0)
            return 1;

        if(memo[y][x] > 0)
            return memo[y][x];

        return memo[y][x] = (getWay(y - 1, x) + getWay(y, x - 1)) % MOD;
    }
}
