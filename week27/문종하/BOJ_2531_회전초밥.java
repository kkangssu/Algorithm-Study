import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*

     회전 초밥 벨트에 놓인 접시의 수 N,
     초밥의 가짓수 d,
     연속해서 먹는 접시의 수 k,
     쿠폰 번호 c

    2 ≤ N ≤ 30,000,
    2 ≤ d ≤ 3,000,
    2 ≤ k ≤ 3,000 (k ≤ N),
    1 ≤ c ≤ d

*/
public class BOJ_2531_회전초밥 {
    static int N, d, k, c, ans;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        solve();

        System.out.println(ans+1);
    }
    static void solve(){
        int [] cnt = new int[d+1];
        int ansCnt = 0;
        for (int i = 0; i < k; i++) {
            if(cnt[arr[i]]++ == 0)ansCnt++;
        }
        ans = cnt[c] == 0 ? ansCnt + 1 : ansCnt;
        for (int i = 0; i < N; i++) {
            if(--cnt[arr[i]]==0)ansCnt--;
            if(cnt[arr[(i+k)%N]]++==0)ansCnt++;

            ans = Math.max(ans, cnt[c] == 0 ? ansCnt + 1 : ansCnt);
        }
    }
}
