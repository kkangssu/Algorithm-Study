import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2422_한윤정이이탈리아에가서아이스크림을사먹는데 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] bad = new boolean[n+1][n+1];
        int[] num = new int[n+1];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bad[a][b] = true;
            bad[b][a] = true;
        }

        int result = 0;
        for(int i = 1; i < n+1; i++){
            for(int j = i+1; j < n+1; j++){
                if(bad[i][j]) continue;
                for(int k = j+1; k < n+1; k++){
                    if(bad[j][k]) continue;
                    if(bad[i][k]) continue;
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
