import java.util.*;
import java.io.*;

public class boj_14712_넴모넴모 {
    static int N, M;
    static boolean[][] visited;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        DFS(0, 0);
        System.out.println(ans);
    }

    private static void DFS(int depth, int start){
        ans += check(depth) ? 1 : 0;

        if(depth == N*M) return; // 격자에 전부 넴모를 놓은 경우

        for(int i = start; i < N*M; i++){
            int r = i/M; // i를 열의 개수로 나눈 몫이 행의 인덱스
            int c = i%M; // i를 열의 개수로 나눈 나머지가 열의 인덱스
            if(visited[r][c]) continue;
            visited[r][c] = true;
            DFS(depth+1, i+1);
            visited[r][c] = false;
        }
    }

    private static boolean check(int depth){
        if(depth < 4) return true;
        for(int i=0; i<N-1; i++){
            for(int j=0; j<M-1; j++){
                if(visited[i][j] && visited[i][j+1] && visited[i+1][j] && visited[i+1][j+1]){
                    return false;
                }
            }
        }
        return true;
    }
}