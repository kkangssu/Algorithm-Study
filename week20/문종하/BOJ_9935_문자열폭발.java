package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1,000,000 * 2
public class BOJ_9935_문자열폭발 {
   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       char[] c = br.readLine().toCharArray();    // 원본 문자열
       char[] b = br.readLine().toCharArray();    // 폭발 문자열
       
       int boom_len = b.length;                   // 폭발 문자열 길이
       char[] buf = new char[1_000_000];          // 결과 저장 버퍼
       int idx = 0;                               // 버퍼의 현재 위치
       int pnt = 0;                               // 현재 폭발 문자열 체크 중인 개수
       int[] step = new int[1_000_000];          // 각 위치별 폭발 문자열 매칭 단계

       for (int i = 0; i < c.length; i++) {
           // 폭발 문자열의 첫 글자와 일치
           if (b[0] == c[i]) {
               if(boom_len == 1) {                // 폭발 문자열이 한 글자면 바로 제거
                   idx--;
               } else {                           // 아니면 매칭 시작
                   ++pnt;
                   step[pnt] = 1;
                   buf[idx] = c[i];
               }
           }
           // 현재 매칭 단계와 일치하는 문자
           else if (b[step[pnt]] == c[i]) {
               if (++step[pnt] == boom_len) {     // 폭발 문자열 완성되면 제거
                   idx -= boom_len;
                   pnt--;
               } else {                           // 아니면 계속 매칭
                   buf[idx] = c[i];
               }
           }
           // 매칭 실패
           else {
               buf[idx] = c[i];
               pnt = 0;
           }
           idx++;
       }

       // 결과 출력
       if (idx != 0)
           System.out.println(Arrays.copyOf(buf, idx));
       else
           System.out.println("FRULA");
   }
}