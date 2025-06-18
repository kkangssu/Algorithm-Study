import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            int n = Integer.parseInt(br.readLine());
            int[][] cost = new int[n+1][n+1];
            int[] size = new int[n+1];
            int[] sum = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < n+1; i++){
                size[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + size[i];
            }

            for(int i = 1; i < n+1; i++){
                for (int start = 1; start < n+1-i; start++) {
                    int end = start + i;
                    cost[start][end] = Integer.MAX_VALUE;
                    for(int j = start; j < end; j++){
                        cost[start][end] = Math.min(cost[start][end], cost[start][j] + cost[j+1][end] + sum[end] - sum[start-1]);
                    }
                }
            }
            sb.append(cost[1][n]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
