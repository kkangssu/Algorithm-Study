package week35.EliteZer0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9527_1의개수세기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        // 누적합을 이용해 [A, B] 구간의 총 1의 개수 계산
        System.out.println(countOnes(B) - countOnes(A - 1));
    }

    /**
     * 1부터 n까지의 수들을 이진수로 표현했을 때
     * 모든 숫자에서 1이 등장한 총 개수를 반환하는 함수
     */
    public static long countOnes(long n) {
        long result = 0; // 결과값 저장
        long bit = 1; // 현재 검사할 비트 위치 (2^0부터 시작)

        // bit가 n보다 작거나 같을 때까지 반복
        while (bit <= n) {
            // 한 쌍(0~bit-1, bit~2bit-1)의 묶음이 몇 번 반복되는지 계산
            long totalPairs = (n + 1) / (bit << 1); // (bit << 1) == bit * 2

            // 마지막 남는 구간에서 bit 이상인 부분만큼 추가적으로 1이 나오는 개수
            long remainder = (n + 1) % (bit << 1);

            // 전체 반복 구간에서 나오는 1의 개수 + 나머지 구간에서 나오는 추가 1의 개수
            result += totalPairs * bit + Math.max(0, remainder - bit);

            // 다음 비트 자리로 이동 (2^0 → 2^1 → 2^2 ...)
            bit <<= 1;
        }

        return result; // 최종적으로 누적된 1의 개수 반환
    }

}
