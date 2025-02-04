import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1516_게임개발_deque {
    private static final int END_MARKER = -1;

    static class Building {
        int time;
        List<Integer> pre = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Building[] buildings = new Building[N + 1];
        int[] inDegree = new int[N+1];
        int[] buildTime = new int[N+1];

        // 건물 정보 입력
        for(int i = 1; i <= N; i++) {
            buildings[i] = new Building();
            st = new StringTokenizer(br.readLine());
            buildings[i].time = Integer.parseInt(st.nextToken());

            while(true) {
                int preBuilding = Integer.parseInt(st.nextToken());
                if(preBuilding == END_MARKER) break;
                buildings[i].pre.add(preBuilding);
                inDegree[i]++;
            }
        }

        // 22852kb 324ms
        Queue<Integer> q = new ArrayDeque<>();

        // 초기 진입차수가 0인 건물 큐에 추가
        for (int i = 1; i <= N; i++) {
            buildTime[i] = buildings[i].time;
            if(inDegree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()) {
            int current = q.poll();

            // 현재 건물을 선수건물로 가지는 모든 건물 검사
            for (int i = 1; i <= N; i++) {
                if (buildings[i].pre.contains(current)) {
                    buildTime[i] = Math.max(buildTime[i],
                            buildTime[current] + buildings[i].time);
                    if(--inDegree[i] == 0) {
                        q.offer(i);
                    }
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            sb.append(buildTime[i]).append('\n');
        }
        System.out.print(sb);
    }
}
