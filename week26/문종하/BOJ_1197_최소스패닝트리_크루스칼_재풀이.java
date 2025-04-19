import java.io.*;

public class BOJ_1197_최소스패닝트리_크루스칼_재풀이 {

    static int V, E;
    static int [] p;
    static Edge [] e;
    static class Edge implements Comparable<Edge>{
        int a, b, w;
        public Edge(int aa, int bb, int ww) {
            a = aa;
            b = bb;
            w = ww;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
    public static void main(String[] args) throws IOException {

        V = readInt();
        E = readInt();

        p = new int[V+1];
        e = new Edge[E];

        for (int i = 0; i <E; i++)
            e[i] = new Edge(readInt(),readInt(),readInt());

        for (int i = 1; i <= V; i++)
            makeSet(i);

        int cnt = 0;
        int min = 0;
        for (int i = 0; i < E; i++) {
            int fA = findSet(e[i].a);
            int fB = findSet(e[i].b);
            if(fA!=fB){
                union(fA, fB);
                min +=e[i].w;
                cnt++;
            }
            if(cnt == V-1) break;
        }
        System.out.println(min);
    }
    static void makeSet(int x){
        p[x]=x;
    }
    static int findSet(int x){
        if(p[x]!=x)
            p[x] = findSet(p[x]);
        return p[x];
    }
    static void union(int x, int y){
        p[findSet(y)] = findSet(x);
    }
    static int readInt() throws IOException {
        int result = 0;
        int read = System.in.read();
        boolean isMinus = false;
        while(read > '9' || read < '0') {
            if(read=='-')isMinus=true;
            read = System.in.read();
        }
        while(read >= '0' && read <= '9') {
            result = result * 10 + read-'0';
            read = System.in.read();
        }
        return isMinus?-result:result;
    }
}
