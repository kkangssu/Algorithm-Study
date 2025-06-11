import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int n, m, k;
    static List<FireBall>[][] board;
    static List<FireBall> balls;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1 ,1 ,1, 0, -1, -1, -1};

    public static class FireBall{
        int r, c, m, s, d;
        FireBall(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new ArrayList[n + 1][n + 1];
        balls = new ArrayList<>();
        for(int i=1; i <= n; i++){
            for(int j=1; j <= n; j++){
                board[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); 
            int c = Integer.parseInt(st.nextToken()); 
            int m = Integer.parseInt(st.nextToken()); 
            int s = Integer.parseInt(st.nextToken()); 
            int d = Integer.parseInt(st.nextToken()); 
            board[r][c].add(new FireBall(r, c, m, s, d));
            balls.add(new FireBall(r, c, m, s, d));
        }

        while(k --> 0){
            moveFireBall();

            for(int i=1; i <=n ; i++){
                for(int j=1; j <= n; j++){
                    if(board[i][j].size() >= 2){
                        combineAndDivide(i, j, board[i][j]);
                    }
                }
            }

            cleanList();
        }

        int answer = 0;
        for(FireBall ball: balls){
            answer += ball.m;
        }

        System.out.println(answer);
    }

    public static void cleanList(){
        balls = new ArrayList<>();
        for(int i=1; i <= n; i++){
            for(int j=1; j <= n; j++){
                if(board[i][j].size() > 0){
                    for(FireBall b: board[i][j]){
                        balls.add(b);
                    }
                }
            }
        }
    }

    public static void moveFireBall(){
        for(int i=1; i <= n; i++){
            for(int j=1; j <= n; j++){
                board[i][j] = new ArrayList<>();
            }
        }

        for(FireBall ball: balls){
            int nr = ball.r + dr[ball.d] * (ball.s % n);
            int nc = ball.c + dc[ball.d] * (ball.s % n);

            if(nr <= 0) nr += n;
            if(nc <= 0) nc += n;
            if(nr > n) nr -= n;
            if(nc > n) nc -= n;

            ball.r = nr;
            ball.c = nc;
            board[nr][nc].add(ball);       
        }
    }

    public static void combineAndDivide(int r, int c, List<FireBall> balls){
        int m_sum = 0, s_sum = 0;
        boolean isEven = true;
		boolean isOdd = true;
        for(FireBall ball: balls){
            m_sum += ball.m;
            s_sum += ball.s;
            if(ball.d % 2 == 0) {
				isOdd = false;
			} else {
				isEven = false;
			}
        }

        int nm = m_sum / 5;
        int ns = s_sum / balls.size();
        int[] dirs = {0, 2, 4, 6};
        if(!isOdd && !isEven){
            dirs[0] = 1; dirs[1] = 3; dirs[2] = 5; dirs[3] = 7;
        }
    
        board[r][c] = new ArrayList<>();
        if(nm <= 0) return;
        for(int dir: dirs){
            board[r][c].add(new FireBall(r, c, nm, ns, dir));
        }
    }
}
