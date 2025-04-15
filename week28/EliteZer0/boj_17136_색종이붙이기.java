import java.util.*;
import java.io.*;
public class Main {
    static int[] paperCnt = {0, 5, 5, 5, 5, 5};
    static int[][] map = new int[10][10];
    static int ans = 26;//색종이 최대 개수는 25니까

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DFS(0, 0, 0);

        if(ans == 26) {
            ans = -1;
        }

        System.out.println(ans);
    }

    private static void DFS(int r, int c, int cnt){
        // 모든 칸을 다 확인한 경우
        if (r >= 9 && c > 9){
            ans = Math.min(ans, cnt);
            return;
        }

        // 이미 찾은 최소 개수보다 많은 색종이를 사용한 경우 가지치기
        if (cnt >= ans){
            return;
        }

        // 행이 끝나면 다음 행으로 이동
        if (c > 9){
            DFS(r + 1, 0, cnt);
            return;
        }

        if(map[r][c] == 1){
            // 큰 색종이부터 시도
            for (int i = 5; i > 0; i--){
                if (paperCnt[i] == 0) continue; // 해당 크기의 색종이를 모두 사용한 경우

                // 색종이를 붙일 수 있는지 확인
                if (canAttach(r, c, i)) {
                    // 색종이 붙이기
                    attach(r, c, i, 0);
                    paperCnt[i]--;

                    // 다음 위치로 이동
                    DFS(r, c+i, cnt+1);

                    // 원상복구 (백트래킹)
                    attach(r, c, i, 1);
                    paperCnt[i]++;
                }
            }
        } else {
            // 현재 위치가 0이면 다음 위치로 이동
            DFS(r, c+1, cnt);
        }
    }

    // 해당 위치에 크기가 size인 색종이를 붙일 수 있는지 확인
    private static boolean canAttach(int r, int c, int size) {
        if (r + size > 10 || c + size > 10) return false;

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }

    // 색종이 붙이기/떼기 (val: 0은 붙이기, 1은 떼기)
    private static void attach(int r, int c, int size, int val) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                map[i][j] = val;
            }
        }
    }
}