import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] spots = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < M; i++)
            spots[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(spots);

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(binarySearch(spots, L, y, x))
                cnt++;
        }

        System.out.println(cnt);
    }

    static boolean binarySearch(int[] arr, int L, int y, int x) {
        int left = 0;
        int right = arr.length;

        while(left < right) {
            int mid = (left + right) / 2;
            int dis = Math.abs(x - arr[mid]) + y;

            if(dis <= L)
                return true;

            if(x == arr[mid])
                return false;
            else if(x < arr[mid])
                right = mid;
            else
                left = mid + 1;
        }

        return false;
    }
}
