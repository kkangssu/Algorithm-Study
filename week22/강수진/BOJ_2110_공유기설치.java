package 강수진;

import java.util.*;
import java.io.*;

public class BOJ_2110_공유기설치 {

    static int N, C;
    static int[] homes;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        homes = new int[N];
        for(int i = 0; i < N; i++){
            homes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(homes);

        int l = 1;
        int r = homes[N-1] - homes[0] + 1;
        int m = 1;
        int router = 1;

        while(l < r){
            int s = homes[0];
            m = (l + r)/2;
            for(int i = 1; i < N; i++){
                if(homes[i] - s < m) continue;
                s = homes[i];
                router++;
            }
            if(router < C) r = m;
            else l = m + 1;

            router = 1;
        }

        System.out.println(l-1);
    }
}
