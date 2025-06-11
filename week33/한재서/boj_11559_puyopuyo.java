import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static char[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static ArrayList<Node> list;
    static int n = 12, m = 6;

    static class Node {
        int y, x;
        
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        board = new char[n][m];
        for(int i = 0; i < n; i++) {
            String field = br.readLine();
            for(int j = 0; j < m; j++) {
                board[i][j] = field.charAt(j); 
            }
        }
        
        int count = 0;
        while(true) {
            boolean isFinished = true;
            visited = new boolean[n][m];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(board[i][j] != '.') {
                        list = new ArrayList<>();
                        bfs(board[i][j], i, j);
                        
                        if(list.size() >= 4) {
                            isFinished = false;
                            for(int k = 0; k < list.size(); k++) {
                                board[list.get(k).y][list.get(k).x] = '.';
                            }
                        }
                    }
                }
            }
            if(isFinished) break;
            fallPuyos();
            count++;
        }
        System.out.println(count);
    }

    static void fallPuyos() {        
        for(int i = 0; i < m; i++) {
            for(int j = n - 1; j > 0; j--) {
                if(board[j][i] == '.') {
                    for(int k = j - 1; k >= 0; k--) {
                        if(board[k][i] != '.') {
                            board[j][i] = board[k][i];
                            board[k][i] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    static void bfs(char c, int y, int x) {
        Queue<Node> q = new LinkedList<>();
        list.add(new Node(y, x));
        q.offer(new Node(y, x));
        visited[y][x] = true;
        
        while(!q.isEmpty()) {
            Node current = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int ny = current.y + dy[i];
                int nx = current.x + dx[i];
                
                if(check(ny, nx, c)) {
                    visited[ny][nx] = true;
                    list.add(new Node(ny, nx));
                    q.offer(new Node(ny, nx));
                }
            }
        }
    }

    static boolean check(int y, int x, char c) {
        return y >= 0 && y < n && x >= 0 && x < m && visited[y][x] == false && board[y][x] == c;
    }
}
