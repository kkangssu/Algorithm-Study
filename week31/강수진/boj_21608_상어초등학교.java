import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map, friend;
    static int n;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        Queue<Integer> que = new ArrayDeque<>();
        friend = new int[n*n+1][4];
        for(int i = 0; i < n*n; i++){
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            que.offer(student);
            for(int j = 0; j < 4; j++){
                friend[student][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        while(!que.isEmpty()){
            seat(que.poll());
        }

        System.out.println(sum());
    }

    static void seat(int s){
        int maxLike = -1;
        int maxAdj = -1;
        int locR = -1;
        int locC = -1;
    
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                if(map[r][c] != 0) continue;
            
                int adj = 0;
                int like = 0;
            
                for(int i = 0; i < 4; i++){
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(!check(nr, nc)) continue;
                
                    if(map[nr][nc] == 0) adj++;
                    else {
                        for(int j = 0; j < 4; j++){
                            if(map[nr][nc] == friend[s][j]){
                                like++;
                                break;
                            }
                        }
                    }
                }
            
                if(like > maxLike || (like == maxLike && adj > maxAdj)) {
                        maxLike = like;
                        maxAdj = adj;
                        locR = r;
                        locC = c;
                }
            }
        }
    
        map[locR][locC] = s;
    }

    static int sum(){
        int sum = 0;
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                int like = 0;
                int s = map[r][c];
                
                for(int i = 0; i < 4; i++){
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(!check(nr, nc)) continue;
                    for(int j = 0; j < 4; j++){
                        if(map[nr][nc] == friend[s][j]){
                            like++;
                            continue;
                        }
                    }
                }
                if(like == 1) sum += 1;
                else if(like == 2) sum += 10;
                else if(like == 3) sum += 100;
                else if(like == 4) sum += 1000;
            }
        }
        return sum;
    }

    static boolean check(int r, int c){
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}
