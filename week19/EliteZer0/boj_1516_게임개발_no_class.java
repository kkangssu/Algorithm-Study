import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 19848kb 196ms
public class boj_1516_게임개발_no_class {
    private static final int END_MARKER = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] edgeCount = new int[N+1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] buildTime = new int[N+1];
        int[] totalBuildTime = new int[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            buildTime[i] = time;
            totalBuildTime[i] = time;

            while(st.hasMoreTokens()) {
                int adj = Integer.parseInt(st.nextToken());
                if(adj == END_MARKER) break;
                graph.get(adj).add(i);
                edgeCount[i]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if(edgeCount[i] == 0) q.add(i);
        }

        while(!q.isEmpty()) {
            int node = q.poll();
            ArrayList<Integer> adjs = graph.get(node);

            for (int adj : adjs) {
                edgeCount[adj]--;
                totalBuildTime[adj] = Math.max(totalBuildTime[adj], totalBuildTime[node] + buildTime[adj]);
                if(edgeCount[adj] == 0) q.add(adj);
            }
        }

        for(int i = 1; i <= N; i++) {
            sb.append(totalBuildTime[i]).append('\n');
        }

        System.out.print(sb.toString());
    }
}