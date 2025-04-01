import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    
    static char[] str;
    static int S, P;
    static int[] cond;
    static int[] state;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        str = br.readLine().toCharArray();
        cond = new int[4];
        st= new StringTokenizer(br.readLine());
        for(int i = 0; i < 4 ; i++){
            cond[i] = Integer.parseInt(st.nextToken());
        }
        state = new int[4];
        for(int i = 0; i < P ; i++){
            char cur = str[i];
            if(cur == 'A')state[0]++;
            if(cur == 'C')state[1]++;
            if(cur == 'G')state[2]++;
            if(cur == 'T')state[3]++;
        }
        int res = 0;
        if(check()){
            res++;
        }
        int start = 0;
        int end = P;
        while(end < S){
            if(str[start]=='A') state[0]--;
            if(str[start]=='C') state[1]--;
            if(str[start]=='G') state[2]--;
            if(str[start]=='T') state[3]--;
            if(str[end]=='A') state[0]++;
            if(str[end]=='C') state[1]++;
            if(str[end]=='G') state[2]++;
            if(str[end]=='T') state[3]++;
            if(check()){
                res++;
            }
            start++;
            end++;
                
                
        }
        System.out.println(res);
      
    }
    static boolean check(){
        for (int i = 0; i < 4; i++) {
			if (state[i] < cond[i])
				return false;
		}
		return true;
    }
        
}
