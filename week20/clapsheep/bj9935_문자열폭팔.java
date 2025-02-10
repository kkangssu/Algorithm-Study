import java.util.Scanner;
import java.util.Stack;

public class bj9935_문자열폭팔 {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		String input1 = sc.next();
		String input2 = sc.next();
		char[] str = input1.toCharArray();
		char[] bomb = input2.toCharArray();
		int strLeng = str.length;
		int bombLeng = bomb.length;
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < strLeng; i++) {
			char cur = str[i];
			stack.add(cur);
			if(stack.size() >= bombLeng) {
				boolean isBomb = true;
				int cnt = 0;
				for(int j = stack.size()-bombLeng; j < stack.size(); j++) {
					if(stack.get(j)!=bomb[cnt]) {
						isBomb = false;
						break;
					}
					cnt++;
				}
				if(isBomb) {
					for(int j = 0; j < bombLeng; j++) {
						stack.pop();
					}
				}
			}
			
		}
		if(stack.size()==0) {
			System.out.println("FRULA");
		}else {
			for(int i =0; i<stack.size();i++) {
				sb.append(stack.get(i));
			}
			
			System.out.println(sb.toString());
		}
		return;
	}

}
