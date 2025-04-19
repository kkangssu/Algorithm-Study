import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1197_최소스패닝트리_크루스칼 {

    static int ans, V, E;
    static int[] p;
    static int[] dist;
    static final int INF = 987654321;
    static Edge[] arr;
    static class Edge implements Comparable<Edge> {
        int a,b,w;
        public Edge(int a, int b, int w){
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w-o.w;
        }
    }
    public static void main(String[] args) throws IOException {

        V = readInt();
        E = readInt();

        arr = new Edge[E];
        p = new int[V+1];
//        dist = new int[V+1];

        for (int i = 1; i <= V; i++)
            p[i]=i;

        for (int i = 0; i < E; i++) {
            int a = readInt();
            int b = readInt();
            int w = readInt();
            arr[i] = new Edge(a,b,w);
        }

        Arrays.sort(arr);

        int cnt = 0;
        int min = 0;
        for (int i = 0; i < E; i++) {
            int fA =  findSet(arr[i].a);
            int fB = findSet(arr[i].b);
            if(fA != fB){
                union(fA, fB);
                min+=arr[i].w;
                cnt++;
            }
            if(cnt==V-1)break;
        }
        System.out.println(min);
    }
    static void makeSet(int x){
        p[x]=x;
    }
    static int findSet(int x){
        if(p[x]!=x)
            p[x]=findSet(p[x]);
        return p[x];
    }
    static void union(int x, int y) {
        p[findSet(y)]=findSet(x);
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
