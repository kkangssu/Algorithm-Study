package week19.강수진;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1516_게임개발 {
    static int n;
    static int[] time, cnt, result;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        time = new int[n+1];
        cnt = new int[n+1];
        result = new int[n+1];
        list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            st = new StringTokenizer(str);

            time[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int num = Integer.parseInt(st.nextToken());
                if(num == -1) break;
                list[num].add(i);
                cnt[i]++;
            }
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if(cnt[i] == 0) {
                que.add(i);
                result[i] = time[i];
            }
        }
        while(!que.isEmpty()){
            int now = que.poll();
            for(int i : list[now]){
                cnt[i]--;
                result[i] = Math.max(result[i], result[now] + time[i]);
                if(cnt[i] == 0) que.add(i);
            }
        }
        for(int i = 1; i <= n; i++){
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb.toString());

    }
}
