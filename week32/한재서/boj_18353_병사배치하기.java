import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] soldiers = new int[N];
        int[] sorted = new int[N];
        int idx = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++)
            soldiers[i] = Integer.parseInt(st.nextToken());

        sorted[0] = soldiers[0];
        root: for(int i=1; i < N; i++) {
            if(soldiers[i] < sorted[idx - 1])
                sorted[idx++] = soldiers[i];
            else if(soldiers[i] == sorted[idx - 1])
                continue;
            else {
                int left = 0;
                int right = idx;
                while(left < right) {
                    int mid = (left + right) / 2;
                    if(soldiers[i] == sorted[mid])
                        continue root;
                    else if(soldiers[i] < sorted[mid])
                        left = mid + 1;
                    else
                        right = mid;
                }
                sorted[left] = soldiers[i];
            }
        }

        System.out.println(N - idx);
    }
}
