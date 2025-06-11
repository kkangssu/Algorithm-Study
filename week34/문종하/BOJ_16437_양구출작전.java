import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    1_000_000_000
*/
public class BOJ_16437_양구출작전 {
    static int N;
    static class Node{
        boolean isSheep;
        int idx,a,p;
        long sum;
        Node pre;
        List<Node> ch = new ArrayList<>();
        public Node(int idx, boolean isSheep, int a, int p, Node pre) {
            this.idx = idx;
            this.isSheep = isSheep; this.a = a; this.p = p;
            this.sum = isSheep?a:0;
            this.pre = pre;
        }
    }
    static Node [] arr;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new Node[N+1];
        arr[1] = new Node(1,false, 0, 0, null);

        for (int i = 2; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            boolean isSheep = st.nextToken().charAt(0) == 'S';
            int a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            Node node = new Node(i,isSheep, a, p, null);
            arr[i] = node;
        }

        for (int i = 2; i < N+1; i++) {
            arr[i].pre = arr[arr[i].p];
            arr[i].pre.ch.add(arr[i]);
        }

        postorder(arr[1]);
        System.out.println(arr[1].sum);
    }
    static void postorder(Node n) {
        long sum = 0;
        for (Node child : n.ch) {
            postorder(child);
            sum += child.sum;
        }

        if(!n.isSheep)
            sum -= n.a;
        n.sum += sum>0?sum:0;
    }
}
