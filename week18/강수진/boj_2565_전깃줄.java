package week18.강수진;

//메모리: 11636 KB 시간: 64 ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2565_전깃줄 {
    static class Wire implements Comparable<Wire>{
        int s, e;
        Wire(int s, int e){
            this.s = s;
            this.e = e;
        }
        @Override
        public int compareTo(Wire o) {
            return this.s - o.s;
        }
    }
    static int[] lis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int num = Integer.parseInt(br.readLine());
        Wire[] wires = new Wire[num];
        lis = new int[num];
        int len = 0;

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            wires[i] = new Wire(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(wires);

        for (int i = 0; i < num; i++) {
            int n = binarySearch(len, wires[i].e);
            lis[n] = wires[i].e;
            if(n == len) len++;
        }

        System.out.println(num-len);

    }

    static int binarySearch(int len, int now) {
        int left = 0;
        int right = len;

        while(left < right) {
            int mid = (left + right) / 2;
            if(lis[mid] < now) left = mid + 1;
            else right = mid;
        }

        return left;
    }
}
