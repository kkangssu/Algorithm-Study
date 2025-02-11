import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] status = new int[N][N];
        for(int i=0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j < N; j++) {
                status[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=1; i < (1 << N) - 1; i++) {
            int[] link = new int[N];
            int[] start = new int[N];
            int idxL = 0;
            int idxS = 0;
            int statL = 0;
            int statS = 0;
            for(int j=0; j < N; j++) {
                if((i & (1 << j)) != 0) link[idxL++] = j;
                else start[idxS++] = j;
            }

            for(int j=0; j < idxL; j++) {
                for(int k=0; k < idxL; k++) {
                    if(j != k) statL += status[link[j]][link[k]];
                }
            }

            for(int j=0; j < idxS; j++) {
                for(int k=0; k < idxS; k++) {
                    if(j != k) statS += status[start[j]][start[k]];
                }
            }

            int diff = Math.abs(statL - statS);
            if(min > diff) min = diff;
            if(min == 0) break;
        }

        System.out.println(min);
    }
}
