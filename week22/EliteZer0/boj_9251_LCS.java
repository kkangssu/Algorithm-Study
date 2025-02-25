import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 88ms 15984kb
public class boj_9251_LCS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        // LCS 결과를 저장할 2차원 배열 생성
        // 0번 인덱스를 비워두어 초기화를 따로 하지 않아도 되게 함
        int[][] LCS = new int[A.length + 1][B.length + 1];

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < B.length + 1; j++) {
                // 현재 비교하는 두 문자가 같은 경우
                if (A[i - 1] == B[j - 1]) {
                    // 이전 LCS 길이에 1을 더함
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                }else{
                    // 현재 비교하는 두 문자가 다른 경우, 이전 단계들 중 최대 길이를 선택
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }
        System.out.println(LCS[A.length][B.length]);
    }
}
