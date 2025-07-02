import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16988_Baaaaaaaaaduk2_2 {

    static class RC{
        int r, c;
        RC(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int N, M;
    static int[][] map;
    static List<Set<Integer>> zero = new ArrayList<>();
    static List<Integer> num2 = new ArrayList<>();
    static int[] num0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findGroup();
        int n = zero.size();
        num0 = new int[n];
        for(int i = 0; i < n; i++){
            num0[i] = zero.get(i).size();
        }

        int max = 0;
        for(int i = 0; i < N*M; i++){
            for(int j = i+1; j < N*M; j++){
                if(map[i/M][i%M] != 0) continue;
                if(map[j/M][j%M] != 0) continue;
                max = Math.max(max, baduk2(i, j));
            }
        }

        System.out.println(max);
    }

    static void findGroup(){
        boolean[][] visited = new boolean[N][M];
        Queue<RC> que = new ArrayDeque<>();
        int idx = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 2) continue;
                if(visited[i][j]) continue;
                zero.add(new HashSet<>());
                que.offer(new RC(i, j));
                visited[i][j] = true;
                int cnt = 1;
                while(!que.isEmpty()){
                    RC now = que.poll();
                    for(int k = 0; k < 4; k++){
                        int r = now.r + dr[k];
                        int c = now.c + dc[k];
                        if(!check(r, c)) continue;
                        if(visited[r][c]) continue;
                        if(map[r][c] == 1) continue;
                        if(map[r][c] == 0) {
                            zero.get(idx).add(r*M + c);
                            continue;
                        }

                        cnt++;
                        visited[r][c] = true;
                        que.offer(new RC(r, c));
                    }
                }
                num2.add(cnt);
                idx++;
            }
        }
    }

    static int baduk2(int a, int b){
        int[] copy0 = Arrays.copyOf(num0, num0.length);
        for(int i = 0; i < zero.size(); i++){
            for(int num: zero.get(i)){
                if(num == a || num == b) copy0[i]--;
            }
        }

        int result = 0;
        for(int i = 0; i < copy0.length; i++){
            if(copy0[i] == 0) result += num2.get(i);
        }

        return result;
    }

    static boolean check(int r, int c){
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
