import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    10 초 (추가 시간 없음)	1024 MB
    자기 자신이 주변인이거나 일방적으로 주변인인 경우는 없으며,
    전체 양방향 주변인 관계는 1,000,000개를 넘지 않는다.

    군중 속 사람은 주변인의 절반 이상이 루머를 믿을 때
*/
public class BOJ_19538_루머 {
    static int N, M;
    static List<Integer> [] adj;
    static class Node{
        int idx, t, trustCnt;
        boolean flag;
        Node(int idx){
            this.idx = idx;
            this.t = -1;
        }
    }
    static Node [] arr;
    static Queue<Node> q;
    static boolean [] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new Node[N+1];
        adj = new ArrayList[N+1];
        v = new boolean[N+1];
        for (int i = 1; i < N+1; i++)
            adj[i] = new ArrayList<>();

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Node(i);
            int n = Integer.parseInt(st.nextToken());
            while (n!=0) {
                adj[i].add(n);
                n = Integer.parseInt(st.nextToken());
            }
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        q = new ArrayDeque<>();
        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            arr[n].flag = true;
            arr[n].t = 0;
            v[n] = true;
            q.offer(arr[n]);
        }

        bfs();

        for (int i = 1; i < N+1; i++)
            sb.append(arr[i].t).append(" ");

        System.out.println(sb);
    }
    static void bfs(){
        int cnt = q.size();
        int time = 1;

        Queue<Node> qq = new ArrayDeque<>();

        while(!q.isEmpty()){

            Node cur = q.poll();
            int idx = cur.idx;
            for(int num : adj[idx]) {
                arr[num].trustCnt++;
                if((arr[num].trustCnt << 1) < adj[num].size() || v[num])continue;
                v[num] = true;
                qq.offer(arr[num]);
            }

            if(--cnt == 0){
                while(!qq.isEmpty()){
                    Node node = qq.poll();
                    int n = node.idx;
                    if(node.t != -1) continue;
                    node.flag = true;
                    node.t = time;
                    q.offer(node);
                }
                v = new boolean[N+1];
                time++;
                cnt = q.size();
            }
        }
    }
}
