import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//The main method must be in a class named "Main".
class Main {
	static int N, M;
	static Map<Integer, Integer> map;
	static PriorityQueue<int[]> maxQ, minQ;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	 StringTokenizer st = new StringTokenizer(br.readLine());
	 N = Integer.parseInt(st.nextToken());
	 map = new HashMap<>();
	 StringBuilder sb = new StringBuilder();
	 
	 maxQ = new PriorityQueue<int[]>((o1,o2)-> {
		 if(o1[1] != o2[1]) {
			 return o2[1] - o1[1];
		 } 
		 return o2[0]-o1[0];
	 });
	 minQ = new PriorityQueue<int[]>((o1,o2)->{
		 if(o1[1]!=o2[1]) {
			 return o1[1] - o2[1];
		 }
		 return o1[0]-o2[0];
	 });
	 
	 for(int i = 0; i < N; i++) {
		 st = new StringTokenizer(br.readLine());
		 int p = Integer.parseInt(st.nextToken());
		 int l = Integer.parseInt(st.nextToken());
		 map.put(p, l);
		 maxQ.add(new int[] {p,l});
		 minQ.add(new int[] {p,l});
	 }
	 
	 M = Integer.parseInt(br.readLine());
	 
	 for(int i = 0; i < M; i++) {
		 st = new StringTokenizer(br.readLine());
		 String order = st.nextToken();
		 int p,l;
		 
		 if(order.equals("recommend")) {
			 int x = Integer.parseInt(st.nextToken());
			 if(x == 1) {
				while(!maxQ.isEmpty()) {
					int[] idx = maxQ.poll();
					p = idx[0];
					l = idx[1];
					if(!map.containsKey(p) || map.get(p) != l) {
						continue;
					}
					sb.append(p).append('\n');
					maxQ.add(new int[] {p,l});
					break;
				}
			 } else {
					while(!minQ.isEmpty() ) {
						int[] idx = minQ.poll();
						p = idx[0];
						l = idx[1];
						if(!map.containsKey(p) || map.get(p) != l ) {
							continue;
						}
						sb.append(p).append('\n');
						minQ.add(new int[] {p,l});
						break;
					}
			 }
			 
		 } 
		 else if(order.equals("add")) {
			 p = Integer.parseInt(st.nextToken());
			 l = Integer.parseInt(st.nextToken());
			 map.put(p, l);
			 maxQ.add(new int[] {p,l});
			 minQ.add(new int[] {p,l});
		 } 
		 else {
			 p = Integer.parseInt(st.nextToken());
			 map.remove(p);
		 }
		 
		 
	 }
	 System.out.println(sb.toString());
	 
 }

}
