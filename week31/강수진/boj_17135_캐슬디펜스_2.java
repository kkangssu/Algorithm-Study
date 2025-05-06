import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int n, m, d, max, enemy;
    static int[] row;
    
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        row = new int[n];
        
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int r = 0;
            for(int j = 0; j < m; j++){
                if(Integer.parseInt(st.nextToken()) == 1) {
                    r |= (1 << j);
                    enemy++;
                }
            }
            row[i] = r;
        }

        for(int archer = 0; archer < (1 << m); archer++) {
            if(Integer.bitCount(archer) == 3) {
                simulation(archer);
            }
        }

        System.out.println(max);
    }

    static void simulation(int arch){
        int[] copy = Arrays.copyOf(row, n);
        int kill = 0;
        int e = enemy;

        int[] archers = new int[3];
        int idx = 0;
        for(int i = 0; i < m; i++) {
            if((arch & (1 << i)) != 0) {
                archers[idx++] = i;
            }
        }

        while(e > 0) {
            Set<Integer> targets = new HashSet<>();
            
            for(int archer : archers) {
                int target = findTarget(copy, archer);
                if(target != -1) {
                    targets.add(target);
                }
            }
            
            for(int target : targets) {
                int r = target / m;
                int c = target % m;
                
                if((copy[r] & (1 << c)) != 0) {
                    copy[r] &= ~(1 << c);
                    kill++;
                    e--;
                }
            }
            
            e -= Integer.bitCount(copy[n-1]);
            
            for(int i = n-1; i > 0; i--) {
                copy[i] = copy[i-1];
            }
            copy[0] = 0;
        }
        
        max = Math.max(max, kill);
    }

    static int findTarget(int[] copy, int arch){        
        int minDist = 30;
        int tR = -1;
        int tC = -1;

        for(int i = 0; i < n; i++) {
            if(copy[i] == 0) continue;
            
            for(int j = 0; j < m; j++) {
                if((copy[i] & (1 << j)) != 0) {
                    int dist = Math.abs(n - i) + Math.abs(arch - j);

                    if(dist > d) continue;
                    
                    if(dist < minDist || (dist == minDist && j < tC)) {
                        minDist = dist;
                        tR = i;
                        tC = j;
                    }
                }
            }
        }
        
        return tR != -1 ? tR*m + tC : -1;
    }
}
