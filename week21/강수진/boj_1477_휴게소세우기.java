package week21.강수진;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1477_휴게소세우기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+2];
        arr[0] = 0;
        arr[N+1] = L;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int left = 0;
        int right = L;
        int maxD = 0;
        while (left <= right){
            maxD = (left + right) / 2;
            int quo = 0;
            //휴게소 사이의 거리의 최댓값이 maxD일 때 현재 휴게소 사이의 거리를 maxD로 나눈 몫의 합 = M인 경우
            //maxD 간격으로 휴게소를 지을 수 있음
            //근데 간격의 최댓값의 최솟값이므로
            //maxD보다 작은 값으로 다시 시도
            for(int i = 1; i <= N+2; i++){
                int distance = arr[i] - arr[i-1] - 1;
                quo += distance/maxD;
                //maxD로 나누는데 left가 0부터 시작하면 최악의 경우 maxD가 0이 될 수 있음 -> left는 1부터 시작
            }
            if(quo > M) left = maxD+1;
            else right = maxD-1;
        }

        System.out.println(left);
    }
}
