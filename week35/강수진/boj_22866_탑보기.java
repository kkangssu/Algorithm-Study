import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[n+1];
        int[] nearL = new int[n+1];
        int[] nearR = new int[n+1];
        
        Stack<Integer> l = new Stack<>();
        for (int i = 1; i < n+1; i++) {
            while (!l.isEmpty() && h[l.peek()] <= h[i]) {
                l.pop();
            }

            cnt[i] = l.size();
            nearL[i] = l.isEmpty()? -1 : l.peek();

            l.push(i);
        }

        Stack<Integer> r = new Stack<>();
        for (int i = n; i > 0; i--) {
            while (!r.isEmpty() && h[r.peek()] <= h[i]) {
                r.pop();
            }

            cnt[i] += r.size();
            nearR[i] = r.isEmpty()? -1 : r.peek();

            r.push(i);
        }

        for (int i = 1; i < n+1; i++) {
            if(cnt[i] == 0) {
                sb.append("0\n");
                continue;
            }

            int nearD = 987654321;
            int nearB = 987654321;
            int distance = 0;

            if(nearL[i] != -1){
                distance = i - nearL[i];
                if(distance < nearD || 
                  (distance == nearD && nearL[i] < nearB)) {
                    nearD = distance;
                    nearB = nearL[i];
                }
            }
            if(nearR[i] != -1){
                distance = nearR[i] - i;
                if(distance < nearD || 
                   (distance == nearD && nearR[i] < nearB)) {
                    nearD = distance;
                    nearB = nearR[i]; 
        }
            }
            
            sb.append(cnt[i]).append(" ").append(nearB).append("\n");
        }
        System.out.println(sb.toString());
    }
}
