import java.io.*;
import java.util.*;

public class BOJ_2422_한윤정이이탈리아에가서아이스크림을사먹는데 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 아이스크림 종류의 수
        int M = Integer.parseInt(st.nextToken()); // 맛없는 조합의 수

        boolean[][] skip = new boolean[N+1][N+1]; // 맛없는 조합 저장

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            skip[a][b] = true;
            skip[b][a] = true;
        }

        int cnt = 0;

        // 3개 선택 조합
        for (int i = 1; i <= N; i++)
            for (int j = i + 1; j <= N; j++)
                for (int k = j + 1; k <= N; k++)
                    // !맛없는 조합
                    if (!skip[i][j] && !skip[j][k] && !skip[i][k])
                        cnt++;
        
        System.out.println(cnt);
    }
}