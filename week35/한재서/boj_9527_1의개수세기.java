import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long start = Long.parseLong(st.nextToken());
        long end = Long.parseLong(st.nextToken());

        System.out.println(count(end) - count(start - 1));
    }

    static long count(long num) {
        if(num == 0)
            return 0;

        long cnt = 0;
        long powerOfTwo = 1;

        while(powerOfTwo <= num) {
            long totalPairs = (num + 1) / (powerOfTwo * 2);
            long remainder = (num + 1) % (powerOfTwo * 2);

            cnt += totalPairs * powerOfTwo;
            cnt += Math.max(0, remainder - powerOfTwo);

            powerOfTwo *= 2;
        }

        return cnt;
    }
}
