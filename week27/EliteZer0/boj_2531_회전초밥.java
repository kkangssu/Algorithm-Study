import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_2531_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushis = new int[N+k-1];

        for (int i = 0; i < N; i++) {
            sushis[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < k-1; i++) {
            sushis[N+i] = sushis[i];
        }

        int maxDish = 0;
        for (int i = 0; i < N; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < k; j++) {
                set.add(sushis[i+j]);
            }
            maxDish = Math.max(maxDish, set.size());
        }

        System.out.println(maxDish);
    }
}
