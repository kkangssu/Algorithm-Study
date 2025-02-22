import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] connections = new int[500];
        int min = 500;
        int max = 0;
        for(int i=0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            if(min > from) min = from;
            if(max < from) max = from;
            connections[from] = Integer.parseInt(st.nextToken()) - 1;
        }

        int[] sequence = new int[max - min + 1];
        int idx = 1;
        sequence[0] = connections[min];
        for(int i=min + 1; i < max + 1; i++) {
            if(connections[i] > 0) {
                int location = binary(sequence, connections[i], idx);
                sequence[location] = connections[i];
                if(location >= idx) idx++;
            }
        }

        System.out.println(N - idx);
    }

    static int binary(int[] arr, int target, int idx) {
        int left = 0;
        int right = idx;
        while(left < right) {
                int mid = (left + right) >> 1;
            if(arr[mid] < target) left = mid + 1;
            else right = mid;
        }

        return right;
    }
}
