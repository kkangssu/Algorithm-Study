package week32;

import java.util.*;
import java.io.*;
public class boj_18353_병사배치하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 병사들 전투력
        int[] soldiers = new int[N];
        for(int i = 0; i < N; i++){
            soldiers[i] = Integer.parseInt(st.nextToken());
        }
        
       // LDS(Longest Decreasing Subsequence)를 구하기 위한 리스트
       // 실제로는 LIS 알고리즘을 역순으로 적용
       ArrayList<Integer> lds = new ArrayList<>();
       
       // 각 병사에 대해 LDS 구성
       for(int i = 0; i < N; i++) {
           // 현재 병사의 전투력이 들어갈 위치를 이진 탐색으로 찾기
           // Collections.reverseOrder()로 내림차순 정렬된 상태에서 탐색
           int position = Collections.binarySearch(lds, soldiers[i], Collections.reverseOrder());
           
           // 결과가 음수면 삽입될 위치를 양수로 변환
           if(position < 0) position = -(position + 1);
           
           // 위치가 리스트 끝이면 새로운 요소 추가
           if(position >= lds.size()) {
               lds.add(soldiers[i]);
           } 
           // 그렇지 않으면 해당 위치의 값을 현재 병사의 전투력으로 교체
           else {
               lds.set(position, soldiers[i]);
           }
       }
       
       // 전체 병사 수에서 LDS 길이를 빼면 제거해야 할 병사의 수
       System.out.println(N - lds.size());
    }
}