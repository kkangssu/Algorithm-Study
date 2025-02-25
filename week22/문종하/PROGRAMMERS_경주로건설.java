package w22_20250225;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class PROGRAMMERS_경주로건설 {
    static class Solution {
        static int [][][] costs;
        static int [] dy = {-1,1,0,0};
        static int [] dx = {0,0,-1,1};
        static int len;
        public int solution(int[][] board) {
            int answer = 0;
            len = board.length;
            costs = new int[4][len][len];

            for(int h = 0;h<4;h++){
                for(int i = 0;i<len;i++){
                    Arrays.fill(costs[h][i],Integer.MAX_VALUE);
                }
            }

            bfs(costs, board);

            answer = Math.min(costs[3][len-1][len-1],costs[1][len-1][len-1]);
            if(answer==Integer.MAX_VALUE)
                answer = -1;
            return answer;
        }
        static void bfs(int [][][] costs, int[][] board){
            Queue<int[]> q = new ArrayDeque<>();
            for(int i = 0;i<4;i++)
                costs[i][0][0]=0;
            //하
            if(board[1][0]==0)
                q.offer(new int[]{0,0,0,1});
            //우
            if(board[0][1]==0)
                q.offer(new int[]{0,0,0,3});
            while(!q.isEmpty()){
                int [] info = q.poll();
                int y = info[0];
                int x = info[1];
                int cost = info[2];
                int dir = info[3];

                for(int i = 0;i<4;i++){
                    // 이전 방향으로 못 돌아감
                    if(dir==1 && i==2);
                    else if(dir==2 && i==1);
                    else if(Math.abs(dir-i)==1) continue;

                    int yy = y+dy[i];
                    int xx = x+dx[i];
                    int curCost = (dir==i?100:600);
                    if(check(yy,xx) || board[yy][xx] == 1 || costs[i][yy][xx] < cost + curCost)continue;

                    costs[i][yy][xx] = cost+curCost;
                    q.offer(new int[]{yy,xx, costs[i][yy][xx],i});
                }


            }
        }
        static boolean check(int y, int x){
            return y < 0 || y>=len || x<0 || x>=len;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 테스트 케이스 1
        int[][] board1 = {{0,0,0}, {0,0,0}, {0,0,0}};
        System.out.println("테스트 케이스 1 결과: " + sol.solution(board1)); // 예상 결과: 900

        // 테스트 케이스 2
        int[][] board2 = {
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0,1},
                {0,0,1,0,0,0,1,0},
                {0,1,0,0,0,1,0,0},
                {1,0,0,0,0,0,0,0}
        };
        System.out.println("테스트 케이스 2 결과: " + sol.solution(board2)); // 예상 결과: 3800

        // 테스트 케이스 3
        int[][] board3 = {
                {0,0,1,0},
                {0,0,0,0},
                {0,1,0,1},
                {1,0,0,0}
        };
        System.out.println("테스트 케이스 3 결과: " + sol.solution(board3)); // 예상 결과: 2100

        // 테스트 케이스 4
        int[][] board4 = {
                {0,0,0,0,0,0},
                {0,1,1,1,1,0},
                {0,0,1,0,0,0},
                {1,0,0,1,0,1},
                {0,1,0,0,0,1},
                {0,0,0,0,0,0}
        };
        System.out.println("테스트 케이스 4 결과: " + sol.solution(board4)); // 예상 결과: 3200

        // 테스트 케이스 5
        int[][] board5 = {
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,0,1,0,0},
                {1,0,0,0,1},
                {1,1,1,0,0}
        };
        System.out.println("테스트 케이스 5 결과: " + sol.solution(board5)); // 예상 결과: 3000
    }
}