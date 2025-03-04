import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    static boolean[] check;
    static HashSet<String> set;
	public static int solution(String[] user_id, String[] banned_id) {
        check = new boolean[user_id.length];
        set = new HashSet<String>();
        
        for(int i=0; i < banned_id.length; i++) 
        	banned_id[i] = banned_id[i].replace('*', '.');
        
        back(0, "", banned_id, user_id);
        
		return set.size();
	}
	
	public static void back(int depth, String res, String[] banned_id, String[] user_id) {
		if(depth == banned_id.length) {
			String[] arr = res.split(" ");
			Arrays.sort(arr);
			
			String str = "";
			for(String s : arr) str += s;
			set.add(str);
			
			return;
		}
		
		for(int i=0; i < user_id.length; i++) {
			if(check[i] || !user_id[i].matches(banned_id[depth])) continue;			
			check[i] = true;
			back(depth + 1, user_id[i] + " " + res, banned_id, user_id);
			check[i] = false;
		}
	}
}
