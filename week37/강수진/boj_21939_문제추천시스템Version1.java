import java.util.*;
import java.lang.*;
import java.io.*;

class boj_21939_문제추천시스템Version1 {

    static class ProbAsc implements Comparable<ProbAsc> {
        int num, level;
        ProbAsc(int num, int level){
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(ProbAsc o) {
            if(this.level != o.level) return this.level - o.level;
            return this.num - o.num;
        }
    }
    static class ProbDesc implements Comparable<ProbDesc> {
        int num, level;
        ProbDesc(int num, int level){
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(ProbDesc o) {
            if(this.level != o.level) return o.level - this.level;
            return o.num - this.num;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<ProbAsc> asc = new PriorityQueue<>();
        PriorityQueue<ProbDesc> desc = new PriorityQueue<>();
        Map<Integer, Integer> prob = new HashMap<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            asc.offer(new ProbAsc(P, L));
            desc.offer(new ProbDesc(P, L));
            prob.put(P, L);
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("recommend")){
                int n = Integer.parseInt(st.nextToken());

                if(n == 1) {
                    while(prob.get(desc.peek().num) != desc.peek().level) desc.poll();
                    sb.append(desc.peek().num).append("\n");
                }
                else {
                    while(prob.get(asc.peek().num) != asc.peek().level) asc.poll();
                    sb.append(asc.peek().num).append("\n");
                }
            }
            else if(command.equals("add")){
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                asc.offer(new ProbAsc(P, L));
                desc.offer(new ProbDesc(P, L));
                prob.put(P, L);
            }
            else if(command.equals("solved")){
                int P = Integer.parseInt(st.nextToken());
                prob.put(P, 0);
            }
        }

        System.out.println(sb.toString());
    }
}