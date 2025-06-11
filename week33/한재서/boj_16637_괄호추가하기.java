import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	static int result = Integer.MIN_VALUE;
	static ArrayList<Integer> nums = new ArrayList<>();
	static ArrayList<Character> operator = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String input = br.readLine();

		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) nums.add(Character.getNumericValue(input.charAt(i)));
			else operator.add(input.charAt(i));
		}

		find(0, nums.get(0));
		System.out.println(result);
	}

	private static void find(int idx, int total) {
		if (idx == operator.size()) {
			result = Math.max(result, total);
			return;
		}
        
		int cal = calculate(total, nums.get(idx + 1), operator.get(idx));
		find(idx + 1, cal);

		if (idx + 2 <= nums.size() - 1) {
			cal = calculate(total, calculate(nums.get(idx + 1), nums.get(idx + 2), operator.get(idx + 1)), operator.get(idx));
			find(idx + 2, cal);
		}
	}

	private static int calculate(int a, int b, char oper) {
        if (oper == '-') return a - b;
        else if (oper == '+') return a + b;
        else return a * b;
	}
}
