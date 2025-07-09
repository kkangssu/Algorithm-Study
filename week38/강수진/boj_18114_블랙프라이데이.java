import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_18114_블랙프라이데이 {

    static long[] weight;
    static int n;
    static long c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());
        weight = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weight[i] = Long.parseLong(st.nextToken());
            if(weight[i] == c) {
                System.out.println(1);
                return;
            }
        }

        Arrays.sort(weight);

        // 2개 고르는 경우
        if(binarySearch(0, n-1, c)){
            System.out.println(1);
            return;
        }

        // 3개 고르는 경우
        for (int i = 0; i < n-2; i++) {
            if (weight[i] >= c) break;

            if(binarySearch(i+1, n-1, c-weight[i])){
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }

    static boolean binarySearch(int left, int right, long s){
        while (left < right){
            long sum = weight[left] + weight[right];
            if(sum == s){
                return true;
            }
            if(sum < s) left++;
            else right--;
        }
        return false;
    }
}