import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] backwards = new int[N];
        ArrayList<Integer>[] adj = new ArrayList[N];
        for(int i=0; i < N; i++) adj[i] = new ArrayList<>();

        for(int i=0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken()) - 1;
            int back = Integer.parseInt(st.nextToken()) - 1;
            backwards[back]++;
            adj[front].add(back);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i < N; i++) {
            if(backwards[i] == 0) {
                queue.offer(i);
                sb.append(i + 1).append(" ");
            }
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i < size; i++) {
                int cur = queue.poll();
                for(int j=0; j < adj[cur].size(); j++) {
                    int next = adj[cur].get(j);
                    backwards[next]--;
                    if(backwards[next] == 0) {
                        queue.offer(next);
                        sb.append(next + 1).append(" ");
                    }
                }
            }
        }

        System.out.println(sb.toString());
    }
}
