import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] objects = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++) {
            objects[i] = Integer.parseInt(st.nextToken());
            if(objects[i] == C) {
                System.out.println(1);
                return;
            }
        }

        Arrays.sort(objects);

        int start = 0;
        int end = N - 1;
        while(start < end) {
            int weight = objects[start] + objects[end];

            if(weight == C) {
                System.out.println(1);
                return;
            } else if(weight > C) {
                end--;
            } else {
                int left = start + 1;
                int right = end;
                while(left < right) {
                    int mid = (left + right) / 2;

                    if(objects[mid] + weight == C) {
                        System.out.println(1);
                        return;
                    } else if(objects[mid] + weight > C) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }

                start++;
            }
        }

        System.out.println(0);
    }
}
