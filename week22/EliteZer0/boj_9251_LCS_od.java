import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 84ms 11672kb
public class boj_9251_LCS_od {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        // 더 짧은 문자열을 B로 설정
        if (A.length < B.length) {
            char[] temp = A;
            A = B;
            B = temp;
        }

        int[] LCS = new int[B.length + 1];
        int pre;    // 이전 대각선 값을 저장
        int temp;   // 현재 위치의 값을 임시 저장

        for (int i = 1; i <= A.length; i++) {
            pre = 0;    // 행의 시작에서 대각선 값 0으로 초기화
            for (int j = 1; j <= B.length; j++) {
                temp = LCS[j];  // 다음 단계의 대각선 값으로 활용하기 위해 현재 값 임시 저장
                // 두 문자가 같을 때
                if (A[i-1] == B[j-1]) {
                    // 이전 대각선 값에 + 1
                    LCS[j] = pre+1;
                } else {
                    // 위쪽과 왼쪽 중 큰 값을 선택
                    LCS[j] = Math.max(LCS[j], LCS[j-1]);
                }
                pre = temp; // 이전 대각선 값 업데이트
            }
        }

        System.out.println(LCS[B.length]);
    }
}
