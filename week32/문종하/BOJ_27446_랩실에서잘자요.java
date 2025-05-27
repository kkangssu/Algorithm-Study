import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_27446_랩실에서잘자요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 해당 페이지, 페이지 존재 여부
        boolean [] check = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++)
            check[Integer.parseInt(st.nextToken())] = true;

        boolean s = check[1]; // true : "페이지 있음 상태", false "페이지 없음 상태"
        boolean c = check[1]; // => 현재 상태
        int cnt = 1, idx = 0;
        int [] arr = new int[N+1];

        // 숫자 배열 생성
        // 있음 없음 있음 ...
        // 없음 있음 없음 ...
        for (int i = 2; i <= N; i++) {
            if(c==check[i]) {
                cnt++;
            }else{
                arr[idx++] = cnt;
                cnt = 1;
                c = swap(c);
            }
        }
        arr[idx++] = cnt;

        // 첫번째 묶음 설정
        int cur = 0, i = 1;
        if(!s) {
            cur = 5 + (2 * arr[0]);
        }

        // 묶음이 하나 일 경우
        if(idx==1){
            System.out.println(cur);
            return;
        }

        // 시작은 "페이지 없음 상태" 부터
        if(s){
            cur = 5 + (2 * arr[1]);
            i++;
        }

        // "페이지 없음 상태" index 부터 시작
        for (++i; i < idx; i+=2)
            cur += Math.min((5 + 2 * arr[i]), (arr[i-1] + arr[i])*2);

        System.out.println(cur);
    }
    static boolean swap(boolean b){
        return b?false:true;
    }
}
