import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    a b c d e f g h i j k l m n o p q r s t u v w x y z
    a b k d e g h i l m n ng o p r s t u w y

    ng는 무조건 이 알파벳으로 생각
    
    ng를 처리 후 시작
    알파벳 순서 sort
*/
public class BOJ_1599_민식어 {
    static class Node implements Comparable<Node> {
        char [] ch;
        public Node(String line) {
            this.ch = line.toCharArray();
        }

        @Override
        public int compareTo(Node o) {
            int min = Math.min(this.ch.length, o.ch.length);

            for (int i = 0; i < min; i++)
                if(this.ch[i] != o.ch[i])
                    return c[this.ch[i]] - c[o.ch[i]];

            return this.ch.length - o.ch.length;
        }
    }
    static int[] c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // ng => x
        StringTokenizer st = new StringTokenizer("a b k d e g h i l m n x o p r s t u w y");
        c = new int['z'];
        int cnt = 0;
        while (st.hasMoreTokens()) {
            c[st.nextToken().charAt(0)] = cnt++;
        }

        int N = Integer.parseInt(br.readLine());
        List<Node> li = new ArrayList<>();

        for (int i = 0; i < N; i++)
            li.add(new Node(br.readLine().replace("ng", "x")));

        Collections.sort(li);
        for(Node n : li)
            sb.append(n.ch).append("\n");

        System.out.println(sb.toString().replace("x","ng"));
    }
}
