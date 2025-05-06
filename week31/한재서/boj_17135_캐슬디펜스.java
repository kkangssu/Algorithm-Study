// 풀이 보고 품

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Main {
    static int N,M,D;
    static int[] archers = new int[3];
    static int[][] map;
    static int max = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
       
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0;i<N;i++) {
           st = new StringTokenizer(br.readLine());
           for(int j=0;j<M;j++){
              map[i][j] = Integer.parseInt(st.nextToken());
           }
        }
        
        pick(0,0); // 시작
        System.out.println(max);
    }
 
    static void pick(int depth, int start){
        if(depth == 3) {
            max = Math.max(max, game()); // 궁수 셋 뽑으면 게임 시작
            return;
        }

        for(int i=start; i < M; i++) { // 조합
            archers[depth] = i;
            pick(depth + 1, i + 1);
        }
    }
 
    static int game(){
        int cnt = 0;
        int[][] status = new int[N][M];
        
        for(int i=N; i > 0; i--) { // 줄 올라옴
            for(int archer: archers) {
                for(int j=1; j < D + 1; j++) {
                    int temp = attack(status, j, i, archer); // 공격
                    if(temp < 0) continue;
                    cnt += temp;
                    break;
                }
            }
        }

        return cnt;
    }
 
    static int attack(int[][] status, int j, int line, int archer){
        for(int nx = archer - j; nx <= archer + j; nx++){ // 범위 내에서
            int ny = line - (j - Math.abs(nx - archer)); // 범위 내에서
            
            if(ny<0 || ny >= line || nx <0 || nx >= M) continue; // 맵 벗어나는지 체크
            if(map[ny][nx]==0) continue; // 적 아님 패스
            
            if(status[ny][nx] == 0){
                status[ny][nx] = line; // 공격한거 체크
                return 1;
            } else if(status[ny][nx] == line) 
                return 0;
        }
 
        return -1;
    }
}
