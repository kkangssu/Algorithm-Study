import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//The main method must be in a class named "Main".
class Main {
	static int N;
	static List<String> li;
	static Map<String, Integer> count;
	static Set<String> s;
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     N = Integer.parseInt(br.readLine());
     
     s = new HashSet<>();
     li = new ArrayList<>();
     count = new HashMap<>();
     
     for(int i = 0; i < N; i++) {
    	 String x = br.readLine();
    	 
    	 if(count.containsKey(x)) {
    		 int n = count.get(x);
    		 count.put(x, n+1);
    		 li.add(x + "" +(n+1)); 
    		 continue;
    	 }
    	 
    	 int size =x.length();
    	 boolean no = false;
    	 for(int j = 0; j < size; j++) {
    		 String tmp = x.substring(0,j+1);
    		 if(!no && !s.contains(tmp)) {
    			 s.add(tmp);
    			 li.add(tmp);
    			 count.put(x, 1);
    			 no = true;
    		 }
    		 s.add(tmp);
    	 }
    	 if(!no) {
    		li.add(x);
    		count.put(x, 1);
    	 }
    	 
     }
     StringBuilder sb = new StringBuilder();
     for(String x :li) {
    	 sb.append(x).append("\n");
     }
     
     System.out.println(sb.toString());

 }

}
