import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 60308kb 176ms
public class boj_12886_돌그룹 {
    static class StoneGroup{
        int a,b,c;
        public StoneGroup(int a,int b,int c){
            // 항상 작은 순서대로 정렬
            int[] sorted = new int[]{a, b, c};
            Arrays.sort(sorted);
            this.a = sorted[0];
            this.b = sorted[1];
            this.c = sorted[2];
        }
    }
    private static boolean isSame = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 3개의 그룹의 돌의 개수가 같으려면 각 그룹의 합이 3으로 나누어 떨어져야 함.
        if((A + B + C) % 3 != 0) {
            System.out.print(0);
            return;
        }

        bfs(A,B,C);

        System.out.print(isSame ? 1 : 0);
    }

    private static void bfs(int a, int b, int c) {
        Queue<StoneGroup> q = new LinkedList<>();
        // 한 돌 그룹이 가질 수 있는 돌의 개수는 0부터 최대 1500개까지
        // 세 수의 합이 항상 일정하므로, 두 수만 알면 나머지 하나는 자동으로 결정
        boolean[][] visited = new boolean[1501][1501];
        q.offer(new StoneGroup(a, b, c));

        while(!q.isEmpty()) {
            StoneGroup cur = q.poll();

            if(cur.a == cur.b && cur.b == cur.c) {
                isSame = true;
                return;
            }

            int[] stones = {cur.a,cur.b,cur.c};
            for(int i = 0; i<3; i++){
                for(int j = i+1; j<3; j++){
                    if(stones[i] < stones[j]){
                        int[] move = stones.clone();
                        move[i] += stones[i];
                        move[j] -= stones[i];

                        StoneGroup moveGroup = new StoneGroup(move[0], move[1], move[2]);

                        if(!visited[moveGroup.a][moveGroup.b]){
                            visited[moveGroup.a][moveGroup.b] = true;
                            q.offer(moveGroup);
                        }
                    }
                }
            }
        }
    }
}
