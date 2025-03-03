import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] houses = new int[N];
        for(int i=0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);
        int left = 0;
        int right = houses[N - 1] + 1;
        while(left < right) {
            int mid = (left + right) / 2;
            int idx = 0;
            int routers = 1;
            for(int i=1; i < N; i++) {
                if(houses[i] - houses[idx] >= mid) {
                    routers++;
                    idx = i;
                }
            }
            if(routers >= C) left = mid + 1;
            else right = mid;
        }

        System.out.println(left - 1);
    }
}
