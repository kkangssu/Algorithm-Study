import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가
*/
public class BOJ_11559_PuyoPuyo {
    static char [][] board = new char[12][];
    static boolean [][] v;
    static int [] dy = {-1,0,1,0};
    static int [] dx = {0,-1,0,1};
    static List<Character> [] boardList = new ArrayList[6];
    static List<Integer> li;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++)
            board[i] = br.readLine().toCharArray();

        for (int i = 0; i < 6; i++)
            boardList[i] = new ArrayList<Character>();

        // 6열 바닥부터 List 담기
        for (int j = 0; j < 6; j++) {
            for (int i = 11; i >= 0; i--) {
                if(board[i][j]=='.') break;
                boardList[j].add(board[i][j]);
            }
        }

        System.out.println(chain(0));
    }
    static int chain(int cur) {
        v = new boolean[12][6];

        // 하나의 연쇄가 끝날 때마다 없애줘야 함
        // 맥스힙으로 담아서 list에서 remove할 때 오류가 나지 않도록
        Queue<Integer> [] boardQueue = new Queue[6];
        for (int j = 0; j < 6; j++)
            boardQueue[j] = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);

        for (int j = 0; j < 6; j++) {

            for (int i = 0; i < boardList[j].size(); i++) {

                int y = i; int x = j;

                if(v[y][x]) continue;

                li = new ArrayList<>();
                int pos = (y*6)+x;
                li.add(pos);
                v[y][x] = true;
                bfs(y,x);
                if(li.size()<4)continue;
                for(int n : li){
                    int yy = n/6;
                    int xx = n%6;
                    boardQueue[xx].offer(yy);
                }

            }

        }

        boolean flag = false;

        // 연쇄 끝나고 없어진 뿌요 처리
        for (int j = 0; j < 6; j++) {
            if(!boardQueue[j].isEmpty())flag = true;
            while(!boardQueue[j].isEmpty()) {
                int i = boardQueue[j].poll();
                boardList[j].remove(i);
            }
        }

        return flag?chain(cur+1):cur;
    }

    static void bfs(int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y,x});
        char c = boardList[x].get(y);
        while (!q.isEmpty()) {
            int [] info = q.poll();
            int cy = info[0];
            int cx = info[1];

            for (int i = 0; i < 4; i++) {
                int yy = cy+dy[i];
                int xx = cx+dx[i];
                // !범위 or 이미방문 or !같은색상 => 넘어감
                if(check(yy,xx) || v[yy][xx] || c != boardList[xx].get(yy)) continue;
                q.offer(new int[]{yy,xx});
                v[yy][xx] = true;
                int pos = (yy*6)+xx;
                li.add(pos);
            }
        }
    }

    static boolean check(int y, int x) {
        return x>=6 || x<0 || boardList[x].size() <= y || y<0;
    }
}
