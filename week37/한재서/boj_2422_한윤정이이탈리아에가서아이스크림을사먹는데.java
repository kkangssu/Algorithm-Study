import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] dontshake = new boolean[N][N];
        for(int i=0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;

            dontshake[first][second] = true;
            dontshake[second][first] = true;
        }

        int cnt = 0;
        for(int i=0; i < N; i++) {
            for(int j=i + 1; j < N; j++) {
                if(dontshake[i][j])
                    continue;

                for(int k=j + 1; k < N; k++) {
                    if(dontshake[i][k] || dontshake[j][k])
                        continue;

                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
