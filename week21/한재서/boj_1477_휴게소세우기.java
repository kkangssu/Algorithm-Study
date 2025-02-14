import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] restAreas = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++) restAreas[i] = Integer.parseInt(st.nextToken());
        restAreas[N] = L;
        Arrays.sort(restAreas);

        int left = 0;
        int right = L;
        while(left < right) {
            int mid = (left + right) / 2;
            int idx = 0;
            int cnt = 0;
            for(int i=1; i < N + 2; i++) {
                int dis = restAreas[i] - restAreas[i - 1];
                cnt += dis / mid;
                if(dis % mid == 0) cnt--;
            }

            if(cnt <= M) right = mid;
            else left = mid + 1;

            if(right == 1) {
                left = 1;
                break;
            }
        }

        System.out.println(left);
    }
}
