import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dolls = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++) dolls[i] = Integer.parseInt(st.nextToken());
        int lions = 0; int left = 0; int right = 0; int ans = 1000000;
        while(left < N) {
            if(right < N && lions < K) {
                if(dolls[right++] == 1) lions++;
            } else {
                if(lions == K) ans = ans < right - left ? ans : right - left;
                if(dolls[left] == 1) lions--;
                left++;
            }
        }

        System.out.println(ans == 1000000 ? -1 : ans);
    }
}
