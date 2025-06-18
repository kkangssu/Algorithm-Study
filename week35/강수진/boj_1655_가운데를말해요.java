import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pqS = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> pqL = new PriorityQueue<>((o1, o2) -> o1 - o2);

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if(pqS.size() == pqL.size()) pqS.offer(n);
            else pqL.offer(n);

            if(!pqS.isEmpty() && !pqL.isEmpty()) {
                if(pqS.peek() > pqL.peek()) {
                    int temp = pqS.poll();
                    pqS.offer(pqL.poll());
                    pqL.offer(temp);
                }
            }
            sb.append(pqS.peek()).append("\n");
        }

    System.out.println(sb.toString());
    }
}
