import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] white, black;
    static int[][][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        white = new int[1000];
        black = new int[1000];
        dp = new int[1000][16][16];
        int idx = 0;

        String line;
        while((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            white[idx] = Integer.parseInt(st.nextToken());
            black[idx++] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getScore(0, 0, 0, idx));
    }

    static int getScore(int depth, int whiteIdx, int blackIdx, int size) {
        if(whiteIdx == 15 && blackIdx == 15)
            return 0;

        if(depth == size)
            return 0;

        if(dp[depth][whiteIdx][blackIdx] > 0)
            return dp[depth][whiteIdx][blackIdx];

        int ans = getScore(depth + 1, whiteIdx, blackIdx, size);
        if(whiteIdx < 15) ans = Math.max(ans, getScore(depth + 1, whiteIdx + 1, blackIdx, size) + white[depth]);
        if(blackIdx < 15) ans = Math.max(ans, getScore(depth + 1, whiteIdx, blackIdx + 1, size) + black[depth]);

        dp[depth][whiteIdx][blackIdx] = ans;
        return dp[depth][whiteIdx][blackIdx];
    }
}
