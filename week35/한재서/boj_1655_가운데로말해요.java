import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> max = new PriorityQueue<>();
        PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if(max.size() < min.size())
                max.offer(num);
            else
                min.offer(num);

            if(max.size() > 0 && min.size() > 0 && max.peek() < min.peek()) {
                int temp = max.poll();
                max.offer(min.poll());
                min.offer(temp);
            }

            sb.append(min.peek()).append("\n");
        }

        System.out.println(sb.toString());
    }
}
