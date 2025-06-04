import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int n, max;
    static char[] fomul;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;
        fomul = br.readLine().toCharArray();

        solve(2, fomul[0]-'0');

        System.out.println(max);
    }

    static void solve(int idx, int num){
        if(idx == n+1){
            max = Math.max(max, num);
            return ;
        }

        char op = fomul[idx-1];
        // 괄호 넣는 경우
        if(idx + 2 < n){
            int nNum = cal(fomul[idx]-'0', fomul[idx+2]-'0', fomul[idx+1]);
            solve(idx+4, cal(num, nNum, op));
        }
        
        // 괄호 넣지 않는 경우
        solve(idx+2, cal(num, fomul[idx]-'0', op));
    }

    static int cal(int a, int b, char op){
        switch(op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return 0;
    }
}
