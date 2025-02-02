import java.util.*;
public class programmers_단속카메라 {
	static int[][] routes = { { -20, -15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } };
	public static void main(String[] args) {
		Arrays.sort(routes, (o1, o2) -> {
			return o1[1] - o2[1];
		});
		
		int res = 0;
		int camPos = Integer.MIN_VALUE;
		for (int[] r : routes) {
			if(camPos < r[0]) {
				camPos = r[1];
				res++;
			}
		}
		System.out.println(res);
	}

}

