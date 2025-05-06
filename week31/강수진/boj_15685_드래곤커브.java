import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static boolean[][] map = new boolean[101][101];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            curve(Integer.parseInt(st.nextToken()),
                 Integer.parseInt(st.nextToken()),
                 Integer.parseInt(st.nextToken()),
                 Integer.parseInt(st.nextToken()));            
        }

        System.out.println(box());
    }

    static void curve(int c, int r, int d, int g){
        List<Integer> direction = new ArrayList<>();
        direction.add(d);
        for(int i = 0; i < g; i++){
            int size = direction.size();
            for(int j = size - 1; j >= 0; j--){
                direction.add((direction.get(j) + 1)%4);
            }
        }

        map[r][c] = true;
        int nr = r;
        int nc = c;
        for(int dir: direction){
            nr += dr[dir];
            nc += dc[dir];
            map[nr][nc] = true;
        }
    }

    static int box(){
        int n = 0;
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) n++;
            }
        }

        return n;
    }
}
