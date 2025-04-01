import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushis = new int[k];
        int[] check = new int[d];
        int[] initial = new int[k - 1];
        int cnt = 0;
        int hasCoupon = 0;
        int max = 0;
        for(int i=0; i < N + k - 1; i++) {
            int idx = i % k;
            if(sushis[idx] > 0) {
                check[sushis[idx] - 1]--;
                if(check[sushis[idx] - 1] == 0) cnt--;
            }
            if(i < N) {
                sushis[idx] = Integer.parseInt(br.readLine());
                if(i < k - 1) initial[i] = sushis[idx];
            } else {
                sushis[idx] = initial[i - N];
            }
            check[sushis[idx] - 1]++;
            if(check[sushis[idx] - 1] == 1) cnt++;
            if(check[c - 1] > 0) hasCoupon = 0;
            else hasCoupon = 1;
            max = max > cnt + hasCoupon ? max : cnt + hasCoupon;
        }

        System.out.println(max);
    }
}
