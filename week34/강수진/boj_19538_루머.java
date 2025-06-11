import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class P{
        int idx, t;
        P(int idx, int t){
            this.idx = idx;
            this.t = t;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] list = new List[n+1];
        int[] nums = new int[n+1];
        int[] half = new int[n+1];
        int[] time = new int[n+1];
        Arrays.fill(time, -1);
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while(true){
                int num = Integer.parseInt(st.nextToken());
                if(num == 0) break;
                list[i].add(num);
                half[i]++;
            }
            half[i] = (half[i] + 1)/2;
        }

        Queue<P> que = new ArrayDeque<>();
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            que.offer(new P(num, 0));
            time[num] = 0;
        }
        
        while(!que.isEmpty()){
            P rumor = que.poll();
            for(int next: list[rumor.idx]){
                if(time[next] != -1) continue;
                nums[next]++;
                if(nums[next] >= half[next]) {
                    que.offer(new P(next, rumor.t+1));
                    time[next] = rumor.t+1;
                }
            }
        }

        for(int i = 1; i <= n; i++){
            sb.append(time[i]).append(" ");
        }

        System.out.println(sb.toString());
    }
}
