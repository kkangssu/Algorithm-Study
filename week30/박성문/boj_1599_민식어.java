package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//The main method must be in a class named "Main".
class Main {
	static int N;
	static Map<String, Character> map= new HashMap<>();
	static PriorityQueue<String[]> q;
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     N = Integer.parseInt(br.readLine());

     map.put("a", 'a');
     map.put( "b", 'b');
     map.put("k", 'c'); map.put("d",'d'); map.put("e", 'e'); map.put("g", 'f');
     map.put("h", 'g'); map.put("i", 'h'); map.put("l", 'i');
    map.put("m", 'j'); map.put("n", 'k'); map.put("ng", 'l'); 
   map.put("o",'m'); map.put("p",'n'); map.put("r",'o'); map.put("s",'p');
   map.put("t",'q');map.put("u",'r');map.put("w",'s'); map.put("y",'t');
   q = new PriorityQueue<>((o1,o2)->{
	   return o1[1].compareTo(o2[1]);
   });
   StringBuilder sb;
   for(int i = 0; i < N; i++) {
  	 String word = br.readLine();
  	 int size = word.length();
  	 sb =  new StringBuilder();
  	 for(int j = 0; j < size; j++) {
  		 if(word.charAt(j) !='n')
  			 sb.append(map.get(Character.toString(word.charAt(j))));
  		 else {
  			 if(j+1 < size && word.charAt(j+1) == 'g') {
  				 j++;
  				 sb.append(map.get("ng"));
  			 } else {
  	  			 sb.append(map.get(Character.toString(word.charAt(j))));
  			 }
  		 }
  	 }
  	 q.add(new String[] {word,sb.toString()});
   }
   
   sb = new StringBuilder();
   
   while(!q.isEmpty()) {
	   sb.append(q.poll()[0]).append("\n");
   }
   
   System.out.println(sb.toString());

 }

}
