package week21.강수진;

class programmers_거리두기확인하기 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;

    public int[] solution(String[][] places) {
        int tc = places.length;
        char[][] arr = new char[5][5];
        int[] answer = new int[5];
        a:
        for(int t = 0; t < tc; t++){
            visited = new boolean[5][5];
            for(int i = 0; i < 5; i++){
                arr[i] = places[t][i].toCharArray();
            }
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(arr[i][j] == 'P' && !check(arr, i, j)) {
                        answer[t] = 0;
                        continue a;
                    }
                }
            }
            answer[t] = 1;
        }

        return answer;
    }
    static boolean check(char[][] arr, int r, int c){
        visited[r][c] = true;
        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
            if(arr[nr][nc] == 'X') continue;
            if(arr[nr][nc] == 'P') return false;
            if(visited[nr][nc]) return false;

            visited[nr][nc] = true;
        }
        return true;
    }
}