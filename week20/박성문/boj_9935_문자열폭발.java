import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static String word;
	static String n;
	static Stack<Character> s;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		word = st.nextToken();
		st = new StringTokenizer(br.readLine());
		n = st.nextToken();
		s = new Stack<>();
		int nLength = n.length()-1;
		int idx = 0;
		
		while(idx < word.length()) {
			
			s.add(word.charAt(idx));
			int nIdx = nLength;
			
			while(!s.isEmpty() && nIdx >= 0 && s.peek() == n.charAt(nIdx)) {
				//System.out.println(s.peek() + " " + n.charAt(nIdx));
				s.pop();
				nIdx--; 
			}
			
			if(nIdx >=0 && nIdx != nLength) {
				//System.out.println(tt + " " +tmp);
				for(int t = nIdx+1; t <= nLength; t++) {
					s.add(n.charAt(t));
				}
			
			}
			idx++;
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(s.isEmpty()) {
			System.out.println("FRULA");
		} else {
			while(!s.isEmpty()) {
				sb.append(s.pop());
			}
			sb.reverse();
			
			System.out.println(sb.toString());

		}

	}	
}
