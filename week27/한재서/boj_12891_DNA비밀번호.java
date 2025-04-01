import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        char[] dna = br.readLine().toCharArray();
        int[] acgt = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < 4; i++) acgt[i] = Integer.parseInt(st.nextToken());
        int a = 0; int c = 0; int g = 0; int t = 0; int ans = 0;

        for(int i=0; i < S; i++) {
            switch(dna[i]) {
                case 'A': 
                    a++;
                    break;
                case 'C':
                    c++;
                    break;
                case 'G':
                    g++;
                    break;
                case 'T':
                    t++;
                    break;
            }

            if(i > P - 1) {
                switch(dna[i - P]) {
                    case 'A': 
                        a--;
                        break;
                    case 'C':
                        c--;
                        break;
                    case 'G':
                        g--;
                        break;
                    case 'T':
                        t--;
                        break;
                }
            }
            
            if(i < P - 1 || a < acgt[0] || c < acgt[1] || g < acgt[2] || t < acgt[3]) continue;
            ans++;
        }

        System.out.println(ans);
    }
}
