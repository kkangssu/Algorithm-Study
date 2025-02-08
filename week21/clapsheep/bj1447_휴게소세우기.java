import java.io.*;
import java.util.*;

public class bj1447_휴게소세우기 {
	static int N, M, L;
    static int[] pos;
	
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        pos = new int[N+2];
        pos[0] =0;
        pos[N+1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
        	pos[i] = Integer.parseInt(st.nextToken());
		}
        Arrays.sort(pos);
        
        int left = 1;
        int right = L-1;
        while (left <= right) {
        	int mid = (left + right)/2;
        	
        	int cnt = 0;
        	for(int i = 1; i < pos.length; i++) {
        		cnt += (pos[i] - pos[i-1] -1)/mid;
        	}
        	
        	if(cnt <= M) {
        		right = mid-1;
        	}else {
        		left = mid+1;
        	}
        }
        System.out.println(left);
    }

}
