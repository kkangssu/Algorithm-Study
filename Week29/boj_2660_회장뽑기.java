import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
    static final int INF = 100000000;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] relations = new int[N][N];

        for(int i=0; i < N; i++) {
            for(int j=0; j < N; j++) {
                if(i == j)
                    continue;
                relations[i][j] = INF;
            }
        }

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            if(from == -2)
                break;
            int to = Integer.parseInt(st.nextToken()) - 1;

            relations[from][to] = 1;
            relations[to][from] = 1;
        }

        for(int k=0; k < N; k++) {
            for(int i=0; i < N; i++) {
                for(int j=0; j < N; j++)
                    relations[i][j] = Math.min(relations[i][j], relations[i][k] + relations[k][j]);
            }
        }

        int[] scores = new int[N];
        int score = INF;
        int cnt = 0;

        for(int i=0; i < N; i++) {
            for(int j=0; j < N; j++) {
                relations[i][j] = relations[i][j] == INF ? 0 : relations[i][j];
                scores[i] = scores[i] > relations[i][j] ? scores[i] : relations[i][j];
            }

            int temp = 0;
            temp = temp > scores[i] ? temp : scores[i];
            if(temp == score)
                cnt++;
            else if(temp < score)
                cnt = 1;
            
            score = score < temp ? score : temp;
        }

        sb.append(score).append(" ").append(cnt).append("\n");
        for(int i=0; i < N; i++) {
            if(scores[i] == score)
                sb.append(i + 1).append(" ");
        }

        System.out.println(sb.toString());
    }
}
