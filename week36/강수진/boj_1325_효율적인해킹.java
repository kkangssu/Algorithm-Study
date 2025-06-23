import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int n, m;
    static List<Integer>[] list;
    static int[] num;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        num = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[b].add(a);
        }

        bfs();

        int max = 0;
        for (int i = 1; i < n+1; i++) {
            max = Math.max(max, num[i]);
        }
        for (int i = 1; i < n+1; i++) {
            if (num[i] == max) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString());
        
    }

    static void bfs(){
        for (int i = 1; i < n+1; i++) {
            Queue<Integer> que = new ArrayDeque<>();
            boolean[] visited = new boolean[n+1];
            que.offer(i);
            visited[i] = true;
            while (!que.isEmpty()) {
                int now = que.poll();
                for (int next : list[now]){
                    if(visited[next]) continue;
                    que.offer(next);
                    visited[next] = true;
                    num[i]++;
                }
            }
        }
    }
}
