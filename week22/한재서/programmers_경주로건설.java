import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 실패 코드,,, 시간과 체력이 부족하여..... 추후 풀고 정답코드 업로드 하겠습니다
class Solution {
    static int ans, len;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    
    public int solution(int[][] board) {
        ans = 0;
        len = board.length;
        visited = new boolean[len][len];
        return bfs(board);
    }
    
    public int bfs(int[][] board) {
        int[][] field = new int[len][len];
        for(int i=0; i < len; i++) Arrays.fill(field[i], Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0, -1});
        field[0][0] = 0;
        root: while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            visited[cur[0]][cur[1]] = true;
            for(int i=0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                if(ny < 0 || ny >= len || nx < 0 || nx >= len || visited[ny][nx]) continue;
                if(board[ny][nx] == 0) {
                    field[ny][nx] = Math.min(field[ny][nx], field[cur[0]][cur[1]] 
                        + (cur[2] == i ? 100 : 600));
                    if(ny < len && nx < len) queue.offer(new int[] {ny, nx, i});
                }
            }
        }
        
        return field[len - 1][len - 1];
    }
}
