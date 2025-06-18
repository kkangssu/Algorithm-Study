import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] pages = new int[M];
        int[] thesis = new int[N + 1];
        
        for(int i=0; i < M; i++) {
            pages[i] = Integer.parseInt(st.nextToken());
            thesis[pages[i]]++;
        }

        int last = 0;
        int ink = 0;
        for(int i=1; i <= N; i++) {
            if(thesis[i] > 0)
                continue;

            if(last == 0)
                ink += 7;
            else
                ink += Math.min(7, (i - last) * 2);
            
            last = i;
        }

        System.out.println(ink);
    }
}
