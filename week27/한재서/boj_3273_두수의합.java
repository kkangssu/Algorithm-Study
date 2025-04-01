import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i < n; i++) seq[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(seq);
        int x = Integer.parseInt(br.readLine());
        int sum = 0;
        int cnt = 0;
        int left = 0;
        int right = n - 1;
        while(left < right) {
            sum = seq[left] + seq[right];
            if(sum == x) cnt++;
            if(sum < x) left++;
            else right--;
        }

        System.out.println(cnt);
    }
}
