import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
    // 결과값 중 최댓값을 저장할 변수 (초기값은 최소 정수로 설정)
    static int result = Integer.MIN_VALUE;

    // 피연산자와 연산자를 나눠서 저장할 리스트
    static ArrayList<Integer> nums = new ArrayList<>();
    static ArrayList<Character> operator = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력: 수식의 길이 n
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();  // 수식 문자열 입력
        
        // 입력 파싱: 피연산자와 연산자를 분리하여 각각 리스트에 저장
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) 
                nums.add(Character.getNumericValue(input.charAt(i)));  // 숫자
            else 
                operator.add(input.charAt(i));  // 연산자
        }

        // 백트래킹 시작: 인덱스 0, 첫 번째 숫자부터 시작
        find(0, nums.get(0));
        
        // 최종 결과 출력
        System.out.println(result);
    }

    // 백트래킹 함수: idx는 현재 연산자 인덱스, total은 지금까지 계산된 값
    private static void find(int idx, int total) {
        // 기저 조건: 연산자를 다 사용했을 경우
        if (idx == operator.size()) {
            result = Math.max(result, total);  // 최댓값 갱신
            return;
        }

        // 첫 번째 경우: 현재 연산자를 그냥 계산
        int cal = calculate(total, nums.get(idx + 1), operator.get(idx));
        find(idx + 1, cal);

        // 두 번째 경우: 다음 연산까지 괄호를 쳐서 먼저 계산
        if (idx + 2 <= nums.size() - 1) {
            // 괄호친 부분 먼저 계산
            cal = calculate(nums.get(idx + 1), nums.get(idx + 2), operator.get(idx + 1));
            // 그 결과를 현재 total과 계산
            cal = calculate(total, cal, operator.get(idx));
            find(idx + 2, cal);
        }
    }

    // 실제 연산을 수행하는 함수
    private static int calculate(int a, int b, char oper) {
        if (oper == '-') return a - b;
        else if (oper == '+') return a + b;
        else return a * b;
    }
}
