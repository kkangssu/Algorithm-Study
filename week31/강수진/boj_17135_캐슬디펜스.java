import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class RC {
        int r, c;
        RC(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] archer = new int[3];
    static int n, m, d, max, enemy, e;
    static int[][] map, copy;
    
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) enemy++;
            }
        }

        setArcher();

        System.out.println(max);
    }

    static void setArcher(){
        for(int i = 0; i < m-2; i++) {
            for(int j = i+1; j < m-1; j++) {
                for(int k = j+1; k < m; k++) {
                    archer[0] = i;
                    archer[1] = j;
                    archer[2] = k;

                    copy = new int[n][m];
                    for (int r = 0; r < n; r++) {
                        System.arraycopy(map[r], 0, copy[r], 0, m);
                    }
                    
                    simulation();
                }
            }
        }
    }

    static void simulation(){
        int cnt = 0;
        e = enemy;
        while(e > 0){
            List<RC> targets = new ArrayList<>();
            
            for(int i = 0; i < 3; i++) {
                RC target = findTarget(archer[i]);
                if(target != null) {
                    boolean flag = false;
                    for(RC t : targets) {
                        if(t.r == target.r && t.c == target.c) {
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) {
                        targets.add(target);
                    }
                }
            }

            for(RC target : targets) {
                if(copy[target.r][target.c] == 1) {
                    copy[target.r][target.c] = 0;
                    cnt++;
                    e--;
                }
            }

            move();
        }

        max = Math.max(max, cnt);
    }

    static RC findTarget(int arch){
        int minDist = 11;
        int tR = -1;
        int tC = -1;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(copy[i][j] == 0) continue;
                
                int dist = Math.abs(n - i) + Math.abs(arch - j);    
                if(dist <= d) {
                    if(dist < minDist || (dist == minDist && j < tC)) {
                        minDist = dist;
                        tR = i;
                        tC = j;
                    }
                }
            }
        }
        
        if(tR != -1) {
            return new RC(tR, tC);
        }
        return null;
    }

    static void move(){
        for(int j = 0; j < m; j++) {
            if(copy[n-1][j] == 1) {
                copy[n-1][j] = 0;
                e--;
            }
        }
    
        for(int i = n-2; i >= 0; i--) {
            for(int j = 0; j < m; j++) {
                copy[i+1][j] = copy[i][j];
            }
        }
        
        for(int j = 0; j < m; j++) {
            copy[0][j] = 0;
        }
    }
}
