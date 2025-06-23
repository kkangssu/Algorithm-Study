import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trains = new int[N];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int trainIdx = Integer.parseInt(st.nextToken()) - 1;
            int seatIdx = 0;

            switch(command) {
                case 1:
                    seatIdx = Integer.parseInt(st.nextToken()) - 1;
                    trains[trainIdx] |= (1 << seatIdx);
                    break;
                case 2:
                    seatIdx = Integer.parseInt(st.nextToken()) - 1;
                    trains[trainIdx] &= ~(1 << seatIdx);
                    break;
                case 3:
                    trains[trainIdx] <<= 1;
                    trains[trainIdx] &= ~(1 << 20);
                    break;
                case 4:
                    trains[trainIdx] >>= 1;
                    trains[trainIdx] &= ~(1 << 20);
                    break;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int train : trains) {
            set.add(train);
        }
        
        System.out.println(set.size());
    }
}
