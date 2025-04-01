import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ryan = 0;
        int length = n+1;
        int end = 0;
        int start = 0;
        while(end < n){
            if(arr[end] == 1) ryan++;
            //System.out.println(start + " " + end + " " + ryan);
            while(ryan >= k){
                length = Math.min(length, end-start+1);

                if(arr[start] == 1) ryan--;
                start++;
            }
            end++;
        }

        System.out.println(length == n+1 ? -1 : length);
    }
}
