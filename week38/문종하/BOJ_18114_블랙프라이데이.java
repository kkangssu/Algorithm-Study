import java.io.*;
import java.util.*;

/*
    물건 N개
    정수의 무게 C => 만 원
    선택할 수 있는 물건은 최대 3개
    물건 중복 선택 불가능
    물건들의 무게는 모두 다름
*/
public class BOJ_18114_블랙프라이데이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] w = new int[N];
        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
            set.add(w[i]);
        }

        boolean flag = false;

        for (int i = 0; i < N && !flag; i++) {
            if (w[i] == C) {
                flag = true;
                break;
            }

            for (int j = i + 1; j < N && !flag; j++) {
                int sum = w[i] + w[j];
                if (sum == C) {
                    flag = true;
                    break;
                }

                int need = C - sum;
                if (need > 0 && set.contains(need) && need != w[i] && need != w[j]) {
                    flag = true;
                    break;
                }
            }
        }

        System.out.println(flag ? 1 : 0);
    }
}