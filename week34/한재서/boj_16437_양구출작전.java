import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static info[] infos;

    static class info{
        long value;
        boolean kinds;
        public info(long value, boolean kinds) {
            this.kinds = kinds;
            this.value = value;
        }
    }

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        infos = new info[N + 1];
        for(int i=0; i <= N; i++)
            tree.add(new ArrayList<>());
        StringTokenizer st;

        infos[1] = new info(0, true);
        for(int i=2; i <= N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            char t = st.nextToken().charAt(0);
            long a = Long.parseLong(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            tree.get(p).add(i);
            infos[i] = new info(a, t == 'S');
        }

        System.out.println(search(1));
    }

    static long search(int index) {
        long sum = 0;
        for(int next : tree.get(index)){
            sum += search(next);
        }

        if(infos[index].kinds)
            return sum + infos[index].value;
        else
            return (sum - infos[index].value < 0) ? 0 : sum-infos[index].value;
    }
}
