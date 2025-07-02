import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
    static class Problem {
        int num, level;

        Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        TreeSet<Problem> ts = new TreeSet<>((o1, o2) -> {
            if(o1.level == o2.level)
                return o1.num - o2.num;

            return o1.level - o2.level;
        });

        Map<Integer, Integer> list = new HashMap<>();

        for(int i=0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            ts.add(new Problem(P, L));
            list.put(P, L);
        }

        int M = Integer.parseInt(br.readLine());

        for(int i=0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "add":
                    int P = Integer.parseInt(st.nextToken());
                    int L = Integer.parseInt(st.nextToken());
                    ts.add(new Problem(P, L));
                    list.put(P, L);
                    break;
                case "recommend":
                    int num = Integer.parseInt(st.nextToken());
                    sb.append(num == 1 ? ts.last().num : ts.first().num).append("\n");
                    break;
                case "solved":
                    int nP = Integer.parseInt(st.nextToken());
                    int nL = list.get(nP);
                    ts.remove(new Problem(nP, nL));
                    break;
            }
        }

        System.out.println(sb.toString());
    }
}
