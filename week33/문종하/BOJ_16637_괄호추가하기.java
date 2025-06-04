`import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_16637_괄호추가하기 {
    static String ex;
    static int ans = Integer.MIN_VALUE;
    static List<Integer> nums = new ArrayList<>();
    static List<Character> op = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ex = br.readLine();

        // 숫자와 연산자 분리
        for (int i = 0; i < ex.length(); i++) {
            if (i % 2 == 0) {
                nums.add(ex.charAt(i) - '0');
            } else {
                op.add(ex.charAt(i));
            }
        }

        dfs(0, nums.get(0));

        System.out.println(ans);
    }

    // pos: 현재 처리할 연산자의 인덱스, current: 현재까지의 계산 결과
    static void dfs(int pos, int cur) {
        if (pos == op.size()) {
            ans = Math.max(ans, cur);
            return;
        }

        // 1. 현재 연산자를 괄호 없이 그대로 계산
        int next1 = calc(cur, op.get(pos), nums.get(pos + 1));
        dfs(pos + 1, next1);

        // 2. 다음 연산자가 있다면, 다음 연산을 먼저 괄호로 계산
        if (pos + 1 < op.size()) {
            int res = calc(nums.get(pos + 1), op.get(pos + 1), nums.get(pos + 2));
            int next2 = calc(cur, op.get(pos), res);
            dfs(pos + 2, next2);
        }
    }

    static int calc(int a, char op, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
    }
}