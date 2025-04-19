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
        int[] seq = new int[N];
        for(int i=0; i < N; i++) seq[i] = Integer.parseInt(br.readLine());
        Arrays.sort(seq);
        int ans = Integer.MAX_VALUE; int left = 0; int right = 0;

        while(right < N) {
            int diff = seq[right] - seq[left];
            if(diff == M) {
                System.out.println(M);
                return;
            } else if(diff < M) right++;
            else {
                ans = ans > diff ? diff : ans;
                left++;
            }
        }

        System.out.println(ans);
    }
}
