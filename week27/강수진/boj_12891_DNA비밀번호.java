import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int[] rule = new int[4];
        int[] dna = new int[4];
        char[] ch = br.readLine().toCharArray();
        for(int i = 0; i < p; i++){
            dna[match(ch[i])]++;
        }

        boolean password = true;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            rule[i] = Integer.parseInt(st.nextToken());
            if(dna[i] < rule[i]) {
                password = false;
            }
        }

        int result = 0;
        if(password) result++;
        
        for(int i = p; i < s; i++){
            dna[match(ch[i])]++;
            dna[match(ch[i-p])]--;

            password = true;
            for(int idx = 0; idx < 4; idx++){
                if(dna[idx] < rule[idx]) {
                    password = false;
                    break;
                }
            }
            if(password) result++;
        }

        System.out.println(result);
        
    }

    static int match(char ch){
        if(ch == 'A') return 0;
        else if(ch == 'C') return 1;
        else if(ch == 'G') return 2;
        else return 3;
    }
}
