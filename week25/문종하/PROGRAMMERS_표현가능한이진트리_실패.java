import java.util.*;

class PROGRAMMERS_표현가능한이진트리_실패 {
    public int[] PROGRAMMERS_표현가능한이진트리_실패(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);

            // 포화 이진트리로 만들기 위해 필요한 전체 노드 수 계산
            int nodeCount = 1;
            int height = 1;
            while (nodeCount < binary.length()) {
                height++;
                nodeCount = (1 << height) - 1; // 높이가 h인 포화 이진트리의 노드 수는 2^h - 1
            }

            // 부족한 길이만큼 앞에 0 채우기
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nodeCount - binary.length(); j++) {
                sb.append("0");
            }
            sb.append(binary);
            String fullBinary = sb.toString();

            // 이진트리로 표현 가능한지 확인
            answer[i] = isValidBinaryTree(fullBinary, 0, fullBinary.length() - 1) ? 1 : 0;
        }

        return answer;
    }

    private boolean isValidBinaryTree(String binary, int start, int end) {
        if (start > end) {
            return true;
        }

        int mid = (start + end) / 2;
        char rootVal = binary.charAt(mid);

        // 루트가 0이면 자식들도 모두 0이어야 함
        if (rootVal == '0') {
            return isAllZero(binary, start, mid - 1) && isAllZero(binary, mid + 1, end);
        }

        // 루트가 1이면 왼쪽 서브트리와 오른쪽 서브트리 각각 확인
        return isValidBinaryTree(binary, start, mid - 1) && isValidBinaryTree(binary, mid + 1, end);
    }

    private boolean isAllZero(String binary, int start, int end) {
        if (start > end) {
            return true;
        }

        int mid = (start + end) / 2;

        // 루트가 1이면 false 반환
        if (binary.charAt(mid) == '1') {
            return false;
        }

        // 왼쪽과 오른쪽 서브트리 모두 0인지 확인
        return isAllZero(binary, start, mid - 1) && isAllZero(binary, mid + 1, end);
    }

    // 테스트
    public static void main(String[] args) {
        PROGRAMMERS_표현가능한이진트리_실패 PROGRAMMERS_표현가능한이진트리_실패 = new PROGRAMMERS_표현가능한이진트리_실패();

        // 테스트 케이스
        long[] numbers1 = {7, 42, 5};
        int[] result1 = PROGRAMMERS_표현가능한이진트리_실패.PROGRAMMERS_표현가능한이진트리_실패(numbers1);
        System.out.println("테스트 케이스 1 결과: " + Arrays.toString(result1));

        long[] numbers2 = {63, 111, 95};
        int[] result2 = PROGRAMMERS_표현가능한이진트리_실패.PROGRAMMERS_표현가능한이진트리_실패(numbers2);
        System.out.println("테스트 케이스 2 결과: " + Arrays.toString(result2));
    }
}