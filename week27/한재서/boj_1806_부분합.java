import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        long[] seq = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++) {
            seq[i] = Long.parseLong(st.nextToken());
        }

        int len = N + 1; int left = 0; long sum = 0;
        for(int i=0; i < N; i++) {
            sum += seq[i];
            while(sum - seq[left] >= S) {
                sum -= seq[left++];
            }
            if(sum >= S) len = len < i - left + 1 ? len : i - left + 1;
        }

        System.out.println(len == N + 1 ? 0 : len);
    }
}
