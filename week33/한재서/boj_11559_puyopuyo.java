import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    // 보드판 (12행 6열)
    static char[][] board;

    // 4방향 이동: 오른쪽, 아래, 왼쪽, 위
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    // 방문 체크 배열
    static boolean[][] visited;

    // BFS로 찾은 같은 색 뿌요 리스트
    static ArrayList<Node> list;

    // 보드판 크기 상수
    static int n = 12, m = 6;

    // 좌표 정보를 담기 위한 클래스
    static class Node {
        int y, x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 보드판 초기화 및 입력 받기
        board = new char[n][m];
        for(int i = 0; i < n; i++) {
            String field = br.readLine();
            for(int j = 0; j < m; j++) {
                board[i][j] = field.charAt(j); 
            }
        }
        
        int count = 0; // 연쇄 횟수

        // 연쇄가 끝날 때까지 반복
        while(true) {
            boolean isFinished = true; // 이번 턴에 연쇄가 발생했는지 여부
            visited = new boolean[n][m]; // 방문 초기화

            // 보드 전체 탐색
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(board[i][j] != '.') { // 빈 칸이 아닐 경우 탐색 시작
                        list = new ArrayList<>();
                        bfs(board[i][j], i, j); // 같은 색 뿌요 탐색

                        // 4개 이상 연결되어 있으면 터뜨리기
                        if(list.size() >= 4) {
                            isFinished = false; // 이번 턴에 연쇄 발생
                            for(int k = 0; k < list.size(); k++) {
                                board[list.get(k).y][list.get(k).x] = '.'; // 터뜨리기
                            }
                        }
                    }
                }
            }

            if(isFinished) break; // 더 이상 터뜨릴 게 없으면 종료

            fallPuyos(); // 뿌요 떨어뜨리기 (중력 적용)
            count++; // 연쇄 횟수 증가
        }

        System.out.println(count); // 결과 출력
    }

    // 중력 적용 함수: 위에 있는 뿌요들을 아래로 떨어뜨림
    static void fallPuyos() {        
        for(int i = 0; i < m; i++) { // 각 열에 대해
            for(int j = n - 1; j > 0; j--) { // 아래에서 위로 탐색
                if(board[j][i] == '.') { // 빈 칸이면
                    for(int k = j - 1; k >= 0; k--) { // 그 위에서 뿌요 찾기
                        if(board[k][i] != '.') { 
                            board[j][i] = board[k][i]; // 끌어내림
                            board[k][i] = '.'; // 기존 위치는 빈 칸으로
                            break;
                        }
                    }
                }
            }
        }
    }

    // BFS 탐색 함수: 같은 색 뿌요를 모두 탐색
    static void bfs(char c, int y, int x) {
        Queue<Node> q = new LinkedList<>();
        list.add(new Node(y, x)); // 첫 시작 뿌요 추가
        q.offer(new Node(y, x));
        visited[y][x] = true; // 방문 체크

        while(!q.isEmpty()) {
            Node current = q.poll();

            for(int i = 0; i < 4; i++) { // 4방향 탐색
                int ny = current.y + dy[i];
                int nx = current.x + dx[i];

                if(check(ny, nx, c)) { // 유효하고 같은 색이면
                    visited[ny][nx] = true;
                    list.add(new Node(ny, nx)); // 리스트에 추가
                    q.offer(new Node(ny, nx));
                }
            }
        }
    }

    // 유효한 좌표인지, 같은 색인지 확인하는 함수
    static boolean check(int y, int x, char c) {
        return y >= 0 && y < n && x >= 0 && x < m 
            && visited[y][x] == false && board[y][x] == c;
    }
}
