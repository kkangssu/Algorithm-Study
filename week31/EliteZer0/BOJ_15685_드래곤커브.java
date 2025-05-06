import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 100x100 격자 0~100까지므로 101
    static boolean[][] map = new boolean[101][101];
    
    // 동, 북, 서, 남
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    
    static int answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int direction = Integer.parseInt(tokenizer.nextToken());
            int generation = Integer.parseInt(tokenizer.nextToken());
            
            drawDragonCurve(x, y, direction, generation);
        }
        
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 현재 점과 동쪽, 남쪽, 대각선 점 4개가 모두 드래곤 커브 위에 있으면 정사각형
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }
        
        System.out.println(answer);
    }
    
    public static void drawDragonCurve(int x, int y, int direction, int generation) {
        // 드래곤 커브의 방향 변화를 저장할 리스트
        List<Integer> dirList = new ArrayList<>();
        dirList.add(direction);
        
        // 세대별로 드래곤 커브 생성
        for (int i = 1; i <= generation; i++) {
            // 기존 방향들을 거꾸로 순회하면서 각 방향에 1을 더하고 4로 나눈 나머지를 추가
            // 이렇게 하면 기존 드래곤 커브를 시계 반대방향으로 90도 회전한 형태가 만들어짐
            for (int j = dirList.size() - 1; j >= 0; j--) {
                dirList.add((dirList.get(j) + 1) % 4);
            }
        }
        
        // I hate x, y...
        // map[x][y] = true;
        map[y][x] = true;
        
        // 방향 리스트대로 이동하면서 각 점을 표시
        for (Integer dir : dirList) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = true;
        }
    }
}
