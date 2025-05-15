import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N;
    static boolean[][] map = new boolean[101][101];
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static private List<Integer> dirList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for(int i=0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int generation = Integer.parseInt(st.nextToken());

            dirList = new LinkedList<>();
            addDir(dir, generation); // 세대 모양 확인
            setCurve(x, y); // 맵에 그리기
        }

        System.out.println(checkCurve());
    }

    static void addDir(int dir, int generation) {
        dirList.add(dir);
        
		for(int i=1; i <= generation; i++) {
			for(int j=dirList.size() - 1; j >= 0; j--) {
				dirList.add((dirList.get(j) + 1) % 4); // 방향 바꾸면서 추가
			}
		}
	}

  static void setCurve(int x, int y) { // 맵에 표시
    map[x][y] = true;
    int nx = x;
    int ny = y;
    
    for(int i=0; i < dirList.size(); i++) {
      int d = dirList.get(i);
      nx += dx[d];
      ny += dy[d];
      map[nx][ny] = true;
    }
  }

    public static int checkCurve() { // 네모 계산
		int cnt=0;
		for(int i=0; i < 100; i++) {
			for(int j=0; j < 100; j++) {
				if(map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) cnt++;
			}
		}

		return cnt;
	}
}
